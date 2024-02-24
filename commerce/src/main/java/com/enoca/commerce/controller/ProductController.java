package com.enoca.commerce.controller;

import com.enoca.commerce.exception.ErrorResponse;
import com.enoca.commerce.exception.ProductNotFoundException;
import com.enoca.commerce.mapper.ProductMapper;
import com.enoca.commerce.model.entity.Product;
import com.enoca.commerce.model.request.ProductRequestDTO;
import com.enoca.commerce.model.response.ProductResponseDTO;
import com.enoca.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = productService.createProduct(productRequestDTO);
        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.toProductResponseDTO(product);
        return ResponseEntity.created(URI.create("/api/products/" + product.getId())).body(productResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO) {
        Product product = productService.updateProduct(productId, productRequestDTO);
        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.toProductResponseDTO(product);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getproduct/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        try {
            ProductResponseDTO productResponseDTO = productService.getProduct(productId);
            return ResponseEntity.ok(productResponseDTO);
        } catch (ProductNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("Product not found", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
}



