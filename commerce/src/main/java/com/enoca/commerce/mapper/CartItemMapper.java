package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.CartItem;
import com.enoca.commerce.model.request.CartItemRequestDTO;
import com.enoca.commerce.model.response.CartItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemMapper {


    CartItemMapper INSTANCE = Mappers.getMapper( CartItemMapper.class);
    CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO);

    CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem);

}

