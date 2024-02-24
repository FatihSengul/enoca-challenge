package com.enoca.commerce.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDTO {

    private Long productId;

    private Integer quantity;

    private Long customerId;

}

