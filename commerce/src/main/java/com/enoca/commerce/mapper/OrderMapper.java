package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Order;
import com.enoca.commerce.model.request.OrderRequestDTO;
import com.enoca.commerce.model.response.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class);


    Order toOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toOrderResponseDTO(Order order);

}
