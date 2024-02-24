package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.request.CartRequestDTO;
import com.enoca.commerce.model.response.CartResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper( CartMapper.class);

    Cart toCart(CartRequestDTO cartRequestDTO);

    CartResponseDTO toCartResponseDTO(Cart cart);

}

