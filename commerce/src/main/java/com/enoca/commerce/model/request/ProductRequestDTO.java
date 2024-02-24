package com.enoca.commerce.model.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

}
