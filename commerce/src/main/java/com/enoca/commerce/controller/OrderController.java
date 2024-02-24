package com.enoca.commerce.controller;

import com.enoca.commerce.model.request.OrderRequestDTO;
import com.enoca.commerce.model.response.OrderHistoryResponseDTO;
import com.enoca.commerce.model.response.OrderResponseDTO;
import com.enoca.commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

        try {
            OrderResponseDTO orderResponseDTO = orderService.placeOrder(orderRequestDTO.getCustomerId());
            return ResponseEntity.ok().body(orderResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> getOrderForCode(@PathVariable String code) {
        try {
            OrderResponseDTO orderResponseDTO = orderService.getOrderForCode(code);
            return ResponseEntity.ok().body(orderResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAllOrdersForCustomer(@PathVariable Long customerId) {
        try {
            List<OrderHistoryResponseDTO> orderHistoryResponseDTOS = orderService.getAllOrdersForCustomer(customerId);
            return ResponseEntity.ok().body(orderHistoryResponseDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}