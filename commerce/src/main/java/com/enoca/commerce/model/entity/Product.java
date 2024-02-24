package com.enoca.commerce.model.entity;

import com.enoca.commerce.model.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;



}
