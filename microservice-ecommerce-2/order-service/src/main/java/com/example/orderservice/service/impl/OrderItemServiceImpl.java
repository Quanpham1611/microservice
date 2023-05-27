package com.example.orderservice.service.impl;

import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.feign.ProductClient;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;
    @Override
    public OrderItem create(OrderItem orderItem) {
        productClient.updateOrdersCount(orderItem.getProductId(), true);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow();
        orderItem.setQuantity(updatedOrderItem.getQuantity());
        orderItem.setOrder(updatedOrderItem.getOrder());
        orderItem.setProductId(updatedOrderItem.getProductId());
        if(!Objects.equals(orderItem.getProductId(), updatedOrderItem.getProductId())) {
            productClient.updateOrdersCount(updatedOrderItem.getProductId(), true);
        }
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
