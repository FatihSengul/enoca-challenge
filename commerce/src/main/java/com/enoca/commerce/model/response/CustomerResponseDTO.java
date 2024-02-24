package com.enoca.commerce.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private CartResponseDTO cart;

    private List<OrderResponseDTO> orders;

}

