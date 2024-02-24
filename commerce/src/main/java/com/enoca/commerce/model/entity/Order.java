package com.enoca.commerce.model.entity;

import com.enoca.commerce.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItem;

    private String code;

    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST )//!!
    @JsonIgnore
    private List<OrderHistory> orderHistory;

}
