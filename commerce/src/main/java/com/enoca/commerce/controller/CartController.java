package com.enoca.commerce.controller;

import com.enoca.commerce.model.request.CartItemRequestDTO;
import com.enoca.commerce.model.response.CartResponseDTO;
import com.enoca.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCart(@PathVariable Long customerId) {
        try {
            CartResponseDTO cartResponseDTO = cartService.getCart(customerId);
            if (cartResponseDTO != null) {
                return ResponseEntity.ok(cartResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Müşterinin sepeti bulunamadı.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + e.getMessage());
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItemRequestDTO cartItemRequestDTO) {

        try {
            cartService.addProductToCart(cartItemRequestDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove-product")
    public ResponseEntity<?> removeProductFromCart(@RequestBody CartItemRequestDTO cartItemRequestDTO) {

        try {
            cartService.removeProductFromCart(cartItemRequestDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/empty")
    public ResponseEntity<?> emptyCart(@RequestParam Long customerId) {

        try {
            cartService.emptyCart(customerId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody CartItemRequestDTO cartItemRequestDTO) {

        try {
            CartResponseDTO cartResponseDTO = cartService.updateCart(cartItemRequestDTO);
            return ResponseEntity.ok().body(cartResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

