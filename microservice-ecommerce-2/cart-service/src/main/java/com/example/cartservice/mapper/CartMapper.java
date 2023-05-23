package com.example.cartservice.mapper;

import com.example.cartservice.dto.CartItemRequest;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quan.common.dto.cart.CartRequest;
import quan.common.dto.cart.CartResponse;
import quan.common.mapper.BasicMapper;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final BasicMapper basicMapper;
    private final CartService cartService;
    public CartResponse create(CartRequest cartRequest) {
        Cart cart = basicMapper.convertTo(cartRequest, Cart.class);
        return basicMapper.convertTo(cartService.create(cart), CartResponse.class);
    }

    public CartResponse addItem(Long cartId, CartItemRequest cartItemRequest) {
        Cart cart = cartService.addItem(cartId, basicMapper.convertTo(cartItemRequest, CartItem.class));
        return basicMapper.convertTo(cart, CartResponse.class);
    }

    public CartResponse removeItem(Long cartId, Long itemId) {
        return basicMapper.convertTo(cartService.removeItem(cartId, itemId), CartResponse.class);
    }

    public CartResponse getById(Long id) {
        return basicMapper.convertTo(cartService.getById(id), CartResponse.class);
    }

    public CartResponse completeCart(Long id) {
        return basicMapper.convertTo(cartService.completeCart(id), CartResponse.class);
    }

    public CartResponse getByCustomerId(Long id) {
        return basicMapper.convertTo(cartService.getByCustomerId(id), CartResponse.class);
    }

    public void deleteByCustomerId(Long id) {
        cartService.deleteByCustomerId(id);
    }

}
