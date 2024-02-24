package com.enoca.commerce.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryRequestDTO {

    private Long productId;

    private BigDecimal price;

    private Integer quantity;

    private Long orderId;

}

