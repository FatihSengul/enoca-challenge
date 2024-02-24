package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Product;
import com.enoca.commerce.model.request.ProductRequestDTO;
import com.enoca.commerce.model.response.ProductResponseDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T15:26:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductRequestDTO productRequestDTO) {
        if ( productRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productRequestDTO.getName() );
        product.setDescription( productRequestDTO.getDescription() );
        product.setPrice( productRequestDTO.getPrice() );
        product.setStock( productRequestDTO.getStock() );

        return product;
    }

    @Override
    public ProductResponseDTO toProductResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( product.getId() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setPrice( product.getPrice() );
        productResponseDTO.setStock( product.getStock() );

        return productResponseDTO;
    }
}
