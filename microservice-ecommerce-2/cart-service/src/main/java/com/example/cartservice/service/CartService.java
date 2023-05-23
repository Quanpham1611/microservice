package com.example.cartservice.service;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;

public interface CartService {
    Cart create(Cart cart);
    Cart addItem(Long cartId, CartItem cartItem);
    Cart removeItem(Long cartId, Long cartItemId);
    Cart getById(Long id);
    Cart completeCart(Long id);
    Cart getByCustomerId(Long id);
    void deleteByCustomerId(Long id);
}
