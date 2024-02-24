package com.enoca.commerce.mapper;

import com.enoca.commerce.model.entity.Product;
import com.enoca.commerce.model.request.ProductRequestDTO;
import com.enoca.commerce.model.response.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {


    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class);

    Product toProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toProductResponseDTO(Product product);

}

