package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.OrderHistory;
import com.enoca.commerce.model.request.OrderHistoryRequestDTO;
import com.enoca.commerce.model.response.OrderHistoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderHistoryMapper {

    OrderHistoryMapper INSTANCE = Mappers.getMapper( OrderHistoryMapper.class);

    OrderHistory toOrderHistory(OrderHistoryRequestDTO orderHistoryRequestDTO);

    OrderHistoryResponseDTO toOrderHistoryResponseDTO(OrderHistory orderHistory);

    List<OrderHistoryResponseDTO> toOrderHistoryResponseDTOs(List<OrderHistory> orderHistories);


}
