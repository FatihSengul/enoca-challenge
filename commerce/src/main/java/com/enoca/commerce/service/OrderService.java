package com.enoca.commerce.service;


import com.enoca.commerce.mapper.OrderHistoryMapper;
import com.enoca.commerce.mapper.OrderMapper;
import com.enoca.commerce.model.entity.*;
import com.enoca.commerce.model.response.OrderHistoryResponseDTO;
import com.enoca.commerce.model.response.OrderResponseDTO;
import com.enoca.commerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


    public OrderResponseDTO placeOrder(Long customerId) {

        // Müşteriyi ve sepetini alıyoruz.
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Cart cart = customer.getCart();

        // Sepet boşsa hata fırlatıyoruz.
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Sepet boş.");
        }

        // Sipariş kodunu oluşturuyoruz.
        String code = generateOrderCode();

        // Siparişi oluşturuyoruz.
        Order order = new Order();
        order.setCustomer(customer);
        order.setCartItem(new ArrayList<>(cart.getCartItems()));
        order.setCode(code);
        order.setTotalAmount(calculateTotalAmount(cart));
        orderRepository.save(order);

        // Sipariş geçmişini oluşturuyoruz.
        for (CartItem cartItem : order.getCartItem()) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setProduct(cartItem.getProduct());
            orderHistory.setPrice(cartItem.getProduct().getPrice());
            orderHistory.setQuantity(cartItem.getQuantity());
            orderHistory.setOrder(order);
            orderHistoryRepository.save(orderHistory);
        }

        // Sepetin içeriğini boşaltıyoruz.
        cartService.emptyCart(customerId);

        // Siparişi döndürüyoruz.
        return OrderMapper.INSTANCE.toOrderResponseDTO(order);
    }


    public OrderResponseDTO getOrderForCode(String code) {
        Order order = orderRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Sipariş bulunamadı."));
        return OrderMapper.INSTANCE.toOrderResponseDTO(order);
    }


    public List<OrderHistoryResponseDTO> getAllOrdersForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomer_Id(customerId);
        List<OrderHistory> orderHistories = new ArrayList<>();
        for (Order order : orders) {
            orderHistories.addAll(order.getOrderHistory());
        }
        return orderHistoryRepository.findAllByOrderCustomer_Id(customerId)
                .stream()
                .map(OrderHistoryMapper.INSTANCE::toOrderHistoryResponseDTO)
                .collect(Collectors.toList());
    }

    private String generateOrderCode() {
        // Sipariş kodu oluşturma algoritması
        return "ORDER-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private BigDecimal calculateTotalAmount(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
