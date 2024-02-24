package com.enoca.commerce.repository;

import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.model.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

   // List<OrderHistory> findByOrderCustomerId(Long customerId);
    List<OrderHistory> findAllByOrderCustomer_Id(Long customerId);

}
