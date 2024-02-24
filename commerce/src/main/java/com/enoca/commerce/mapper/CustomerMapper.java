package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.model.entity.Order;
import com.enoca.commerce.model.request.CustomerRequestDTO;
import com.enoca.commerce.model.response.CartResponseDTO;
import com.enoca.commerce.model.response.CustomerResponseDTO;
import com.enoca.commerce.model.response.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class);


    Customer toCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO toCustomerResponseDTO(Customer customer);

}

