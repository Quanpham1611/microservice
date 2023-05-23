package com.example.cartservice.service.Impl;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.feign.OrderClient;
import com.example.cartservice.feign.ProductClient;
import com.example.cartservice.repository.CartItemRepository;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quan.common.dto.cart.CartResponse;
import quan.common.dto.catalog.product.ProductResponse;
import quan.common.exceptions.AlreadyExistException;
import quan.common.exceptions.NotFoundException;
import quan.common.mapper.BasicMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BasicMapper basicMapper;
    private final OrderClient orderClient;
    private final ProductClient productClient;
    @Override
    public Cart create(Cart cart) {
        Cart existingCart = getByCustomerId(cart.getCustomerId());
        if(existingCart != null){
            throw new AlreadyExistException("Cart for this customer already exist");
        }
        return cartRepository.save(cart);
    }
    private BigDecimal calculatePrice(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public Cart addItem(Long cartId, CartItem cartItem) {
        Cart cart = getById(cartId);
        ProductResponse productResponse = productClient.getProductById(cartItem.getProductId()).getBody();
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(cartItem::equals).findFirst();
        existingItem.ifPresent(item -> {
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            item.addPrice(calculatePrice(productResponse.getPrice(), cartItem.getQuantity()));
            cartItemRepository.save(item);
        });
        existingItem.orElseGet(() -> {
            cart.addItem(cartItem);
            cartItem.setCart(cart);
            cartItem.setPrice(calculatePrice(productResponse.getPrice(), cartItem.getQuantity()));
            return cartItemRepository.save(cartItem);
        });
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItem(Long cartId, Long itemId) {
        Cart cart = getById(cartId);
        cart.removeItem(itemId);
        cartItemRepository.deleteById(itemId);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFoundException("Cart not found exception"));
    }

    @Override
    @Transactional
    public Cart completeCart(Long id) {
        Cart cart = getById(id);
        orderClient.create(basicMapper.convertTo(cart, CartResponse.class));
        cartItemRepository.deleteAllByCartId(id);
        cart.setCartItems(new ArrayList<>());
        return cart;
    }

    @Override
    public Cart getByCustomerId(Long id) {
        return cartRepository.findByCustomerId(id);
    }

    @Override
    public void deleteByCustomerId(Long id) {
        cartRepository.deleteByCustomerId(id);
    }
}
