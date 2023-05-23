package com.example.cartservice.controller;

import com.example.cartservice.dto.CartItemRequest;
import com.example.cartservice.mapper.CartMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quan.common.dto.cart.CartRequest;
import quan.common.dto.cart.CartResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartMapper cartMapper;
    @PostMapping
    public ResponseEntity<CartResponse> createProduct(@Valid @RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(cartMapper.create(cartRequest));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable("cartId") Long cartId) {
        return ResponseEntity.ok(cartMapper.getById(cartId));
    }

    @PutMapping("/{cartId}/complete")
    public ResponseEntity<CartResponse> completeCart(@PathVariable("cartId") Long cartId) {
        return ResponseEntity.ok(cartMapper.completeCart(cartId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CartResponse> getCartByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(cartMapper.getByCustomerId(customerId));
    }

//    Cart items

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartResponse> createCartItem(@PathVariable("cartId") Long cartId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        return ResponseEntity.ok(cartMapper.addItem(cartId, cartItemRequest));
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartResponse> deleteCartItem(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {
        return ResponseEntity.ok(cartMapper.removeItem(cartId, itemId));
    }}
