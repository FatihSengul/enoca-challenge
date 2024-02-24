package com.enoca.commerce.repository;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.entity.CartItem;
import com.enoca.commerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

     Optional<CartItem> findByCartAndProduct(Cart cart, Product product);


     List<CartItem> findByCart(Cart cart);

    void deleteAllByCart(Cart cart);


}
