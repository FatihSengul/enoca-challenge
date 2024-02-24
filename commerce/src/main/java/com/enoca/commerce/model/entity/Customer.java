package com.enoca.commerce.model.entity;

import com.enoca.commerce.model.common.BaseEntity;
import com.enoca.commerce.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(name = "username", unique = true, length = 100)
    private String username;

    private String email;

    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")//değiştrebilir!!!!
    private Cart cart;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Transient
    private String token;

}
