package com.enoca.commerce.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long cartId;



}

