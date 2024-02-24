package com.enoca.commerce.service;

import com.enoca.commerce.mapper.CartMapper;
import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.entity.CartItem;
import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.model.entity.Product;
import com.enoca.commerce.model.request.CartItemRequestDTO;
import com.enoca.commerce.model.response.CartResponseDTO;
import com.enoca.commerce.repository.CartItemRepository;
import com.enoca.commerce.repository.CartRepository;
import com.enoca.commerce.repository.CustomerRepository;
import com.enoca.commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Cart updateCart(Cart cart) {
        // CartItem'ların güncellenmesini ve kaydedilmesini sağla
        for (CartItem cartItem : cart.getCartItems()) {
            cartItemRepository.save(cartItem);
        }
        return cartRepository.save(cart);
    }

    public void addProductToCart(CartItemRequestDTO cartItemRequestDTO) {

        Long customerId = cartItemRequestDTO.getCustomerId();
        Long productId = cartItemRequestDTO.getProductId();
        Integer quantity = cartItemRequestDTO.getQuantity();

        // Müşteriyi ve ürünü veri tabanından alıyoruz.
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        // Müşterinin sepetini alıyoruz.
        Cart cart = customer.getCart();

        // Eğer müşterinin sepeti yoksa yeni bir sepet oluşturuyoruz.
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart = cartRepository.save(cart);
            customer.setCart(cart);
            customerRepository.save(customer);
        }

        // Sepette ürün olup olmadığını kontrol ediyoruz.
        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product).orElse(null);

        // Ürün sepette varsa miktarını güncelliyoruz.
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            // Ürün sepette yoksa yeni bir sepet öğesi oluşturuyoruz.
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cartItemRepository.save(newCartItem);
        }

        // Sepetin toplam fiyatını güncelliyoruz.
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);

    }

    public void removeProductFromCart(CartItemRequestDTO cartItemRequestDTO) {

        Long customerId = cartItemRequestDTO.getCustomerId();
        Long productId = cartItemRequestDTO.getProductId();

        // Müşteriyi ve ürünü veri tabanından alıyoruz.
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        // Müşterinin sepetini alıyoruz.
        Cart cart = customer.getCart();

        // Sepette ürün olup olmadığını kontrol ediyoruz.
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product).orElse(null);

        // Ürün sepette yoksa hata fırlatıyoruz.
        if (cartItem == null) {
            throw new RuntimeException("Ürün sepete eklenmemiş.");
        }

        // Üründen birden fazla varsa miktarını bir azaltıyoruz.
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        } else {
            // Üründen 1 tane varsa sepetten tamamen kaldırıyoruz.
            cartItemRepository.delete(cartItem);
        }

        // Sepetin toplam fiyatını güncelliyoruz.
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);

    }

    @Transactional
    public void emptyCart(Long customerId) {

        // Müşteriyi veri tabanından alıyoruz.
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        // Müşterinin sepetini alıyoruz.
        Cart cart = customer.getCart();

        // Sepetteki tüm ürünleri siliyoruz.
        cartItemRepository.deleteAllByCart(cart);

        // Sepetin toplam fiyatını 0 olarak güncelliyoruz.
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);

    }


    public CartResponseDTO getCart(Long customerId) {
        // Müşteriyi veritabanından al
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Müşterinin sepeti var mı kontrol et
            Cart cart = customer.getCart();

            if (cart != null) {
                // Sepet varsa DTO'ya dönüştür ve geri döndür
                return CartMapper.INSTANCE.toCartResponseDTO(cart);
            } else {
                // Müşterinin sepeti yoksa null döndür
                return null;
            }
        } else {
            throw new RuntimeException("Müşteri bulunamadı: " + customerId);
        }
    }

    public CartResponseDTO updateCart(CartItemRequestDTO cartItemRequestDTO) {

        Long customerId = cartItemRequestDTO.getCustomerId();
        Long productId = cartItemRequestDTO.getProductId();
        Integer quantity = cartItemRequestDTO.getQuantity();

        // Müşteriyi ve ürünü veri tabanından alıyoruz.
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        // Müşterinin sepetini alıyoruz.
        Cart cart = customer.getCart();

        // Sepette ürün olup olmadığını kontrol ediyoruz.
        CartItem oldCartItem = cartItemRepository.findByCartAndProduct(cart, product).orElse(null);

        // Ürün sepete eklenmemişse hata fırlatıyoruz.
        if (oldCartItem == null) {
            throw new RuntimeException("Ürün sepete eklenmemiş.");
        }

        // Yeni miktarın 0'dan küçük veya ürünün stoğundan fazla olmamasını kontrol ediyoruz.
        if (quantity <= 0 || quantity > product.getStock()) {
            throw new RuntimeException("Geçersiz miktar.");
        }

        // Yeni miktar eski miktar ile aynıysa hiçbir şey yapmıyoruz.
        if (quantity.equals(oldCartItem.getQuantity())) {
            return CartMapper.INSTANCE.toCartResponseDTO(cart);
        }

        // Miktarı güncelliyoruz.
        oldCartItem.setQuantity(quantity);
        cartItemRepository.save(oldCartItem);

        // Sepetin toplam fiyatını güncelliyoruz.
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);

        // Güncellenen sepeti döndürüyoruz.
        return CartMapper.INSTANCE.toCartResponseDTO(cart);
    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

