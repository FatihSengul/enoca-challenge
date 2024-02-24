package com.enoca.commerce.service;

import com.enoca.commerce.mapper.ProductMapper;
import com.enoca.commerce.model.entity.Product;
import com.enoca.commerce.model.request.ProductRequestDTO;
import com.enoca.commerce.model.response.ProductResponseDTO;
import com.enoca.commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.INSTANCE.toProduct(productRequestDTO);
        product.setStock(productRequestDTO.getStock());
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setDeleted(true);
        productRepository.save(product);
    }

    public ProductResponseDTO getProduct(Long productId) {
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findByIdAndDeletedFalse(productId));
        return optionalProduct.map(ProductMapper.INSTANCE::toProductResponseDTO).orElse(null);
    }

}


