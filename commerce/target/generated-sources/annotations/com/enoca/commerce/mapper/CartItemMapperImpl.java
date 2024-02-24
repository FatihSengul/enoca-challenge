package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.CartItem;
import com.enoca.commerce.model.request.CartItemRequestDTO;
import com.enoca.commerce.model.response.CartItemResponseDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T15:26:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO) {
        if ( cartItemRequestDTO == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setQuantity( cartItemRequestDTO.getQuantity() );

        return cartItem;
    }

    @Override
    public CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();

        cartItemResponseDTO.setId( cartItem.getId() );
        cartItemResponseDTO.setProduct( cartItem.getProduct() );
        cartItemResponseDTO.setQuantity( cartItem.getQuantity() );

        return cartItemResponseDTO;
    }
}
