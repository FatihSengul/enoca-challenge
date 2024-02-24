package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Cart;
import com.enoca.commerce.model.entity.CartItem;
import com.enoca.commerce.model.entity.Customer;
import com.enoca.commerce.model.entity.Order;
import com.enoca.commerce.model.request.CustomerRequestDTO;
import com.enoca.commerce.model.response.CartItemResponseDTO;
import com.enoca.commerce.model.response.CartResponseDTO;
import com.enoca.commerce.model.response.CustomerResponseDTO;
import com.enoca.commerce.model.response.OrderResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T15:26:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerRequestDTO customerRequestDTO) {
        if ( customerRequestDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( customerRequestDTO.getFirstName() );
        customer.setLastName( customerRequestDTO.getLastName() );
        customer.setEmail( customerRequestDTO.getEmail() );
        customer.setPassword( customerRequestDTO.getPassword() );

        return customer;
    }

    @Override
    public CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();

        customerResponseDTO.setId( customer.getId() );
        customerResponseDTO.setFirstName( customer.getFirstName() );
        customerResponseDTO.setLastName( customer.getLastName() );
        customerResponseDTO.setEmail( customer.getEmail() );
        customerResponseDTO.setCart( cartToCartResponseDTO( customer.getCart() ) );
        customerResponseDTO.setOrders( orderListToOrderResponseDTOList( customer.getOrders() ) );

        return customerResponseDTO;
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

    protected CartResponseDTO cartToCartResponseDTO(Cart cart) {
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

    protected OrderResponseDTO orderToOrderResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setId( order.getId() );
        orderResponseDTO.setTotalAmount( order.getTotalAmount() );
        orderResponseDTO.setCode( order.getCode() );

        return orderResponseDTO;
    }

    protected List<OrderResponseDTO> orderListToOrderResponseDTOList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderResponseDTO> list1 = new ArrayList<OrderResponseDTO>( list.size() );
        for ( Order order : list ) {
            list1.add( orderToOrderResponseDTO( order ) );
        }

        return list1;
    }
}
