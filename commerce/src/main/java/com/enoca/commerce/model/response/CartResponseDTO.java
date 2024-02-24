package com.enoca.commerce.model.response;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

    private Long id;

    private Customer customer;

    private BigDecimal totalPrice;

    private List<CartItemResponseDTO> cartItems;

}