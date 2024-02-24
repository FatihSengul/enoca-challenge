package com.enoca.commerce.model.response;

import com.enoca.commerce.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {

    private Long id;

    private Product product;

    private Integer quantity;


}

