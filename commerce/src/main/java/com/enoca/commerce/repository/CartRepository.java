package com.enoca.commerce.repository;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.response.CartResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    Cart findByCustomerId(Long customerId);
//
//    CartResponseDTO getCartById(Long customerId);
//
//    Optional<Cart> findByCustomer_Id(Long customerId);

}

