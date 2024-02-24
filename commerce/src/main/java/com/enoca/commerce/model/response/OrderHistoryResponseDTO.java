package com.enoca.commerce.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderHistoryResponseDTO {

    private Long id;

    private Long productId;

    private BigDecimal price;

    private Integer quantity;

    private Long orderId;

    private Long customerId;


}

