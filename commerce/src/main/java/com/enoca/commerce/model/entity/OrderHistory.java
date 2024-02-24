package com.enoca.commerce.model.entity;

import com.enoca.commerce.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory extends BaseEntity {

    @ManyToOne
    private Product product;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private Order order;
}

