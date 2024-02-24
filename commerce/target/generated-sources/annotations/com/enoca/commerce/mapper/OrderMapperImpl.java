package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Order;
import com.enoca.commerce.model.request.OrderRequestDTO;
import com.enoca.commerce.model.response.OrderResponseDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T15:26:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        if ( orderRequestDTO == null ) {
            return null;
        }

        Order order = new Order();

        return order;
    }

    @Override
    public OrderResponseDTO toOrderResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setId( order.getId() );
        orderResponseDTO.setTotalAmount( order.getTotalAmount() );
        orderResponseDTO.setCode( order.getCode() );

        return orderResponseDTO;
    }
}
