package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.OrderHistory;
import com.enoca.commerce.model.request.OrderHistoryRequestDTO;
import com.enoca.commerce.model.response.OrderHistoryResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T15:26:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class OrderHistoryMapperImpl implements OrderHistoryMapper {

    @Override
    public OrderHistory toOrderHistory(OrderHistoryRequestDTO orderHistoryRequestDTO) {
        if ( orderHistoryRequestDTO == null ) {
            return null;
        }

        OrderHistory orderHistory = new OrderHistory();

        orderHistory.setPrice( orderHistoryRequestDTO.getPrice() );
        orderHistory.setQuantity( orderHistoryRequestDTO.getQuantity() );

        return orderHistory;
    }

    @Override
    public OrderHistoryResponseDTO toOrderHistoryResponseDTO(OrderHistory orderHistory) {
        if ( orderHistory == null ) {
            return null;
        }

        OrderHistoryResponseDTO orderHistoryResponseDTO = new OrderHistoryResponseDTO();

        orderHistoryResponseDTO.setId( orderHistory.getId() );
        orderHistoryResponseDTO.setPrice( orderHistory.getPrice() );
        orderHistoryResponseDTO.setQuantity( orderHistory.getQuantity() );

        return orderHistoryResponseDTO;
    }

    @Override
    public List<OrderHistoryResponseDTO> toOrderHistoryResponseDTOs(List<OrderHistory> orderHistories) {
        if ( orderHistories == null ) {
            return null;
        }

        List<OrderHistoryResponseDTO> list = new ArrayList<OrderHistoryResponseDTO>( orderHistories.size() );
        for ( OrderHistory orderHistory : orderHistories ) {
            list.add( toOrderHistoryResponseDTO( orderHistory ) );
        }

        return list;
    }
}
