package com.enoca.commerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private BigDecimal totalAmount;

    private Long customerId;

    private List<CartItemResponseDTO> cartItems;

    private String code;

}

