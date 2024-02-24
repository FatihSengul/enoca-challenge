package com.enoca.commerce.repository;

import com.enoca.commerce.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCode(String code);


    List<Order> findByCustomer_Id(Long customerId);



}

