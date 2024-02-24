package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.entity.CartItem;
import com.enoca.commerce.model.request.CartRequestDTO;
import com.enoca.commerce.model.response.CartItemResponseDTO;
import com.enoca.commerce.model.response.CartResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T15:26:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart toCart(CartRequestDTO cartRequestDTO) {
        if ( cartRequestDTO == null ) {
            return null;
        }

        Cart cart = new Cart();

        return cart;
    }

    @Override
    public CartResponseDTO toCartResponseDTO(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponseDTO cartResponseDTO = new CartResponseDTO();

        cartResponseDTO.setId( cart.getId() );
        cartResponseDTO.setCustomer( cart.getCustomer() );
        cartResponseDTO.setTotalPrice( cart.getTotalPrice() );
        cartResponseDTO.setCartItems( cartItemListToCartItemResponseDTOList( cart.getCartItems() ) );

        return cartResponseDTO;
    }

    protected CartItemResponseDTO cartItemToCartItemResponseDTO(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();

        cartItemResponseDTO.setId( cartItem.getId() );
        cartItemResponseDTO.setProduct( cartItem.getProduct() );
        cartItemResponseDTO.setQuantity( cartItem.getQuantity() );

        return cartItemResponseDTO;
    }

    protected List<CartItemResponseDTO> cartItemListToCartItemResponseDTOList(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemResponseDTO> list1 = new ArrayList<CartItemResponseDTO>( list.size() );
        for ( CartItem cartItem : list ) {
            list1.add( cartItemToCartItemResponseDTO( cartItem ) );
        }

        return list1;
    }
}
