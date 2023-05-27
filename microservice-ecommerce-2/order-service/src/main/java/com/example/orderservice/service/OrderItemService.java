package com.example.orderservice.service;

import com.example.orderservice.entity.OrderItem;

public interface OrderItemService {
    OrderItem create(OrderItem orderItem);
    OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem);
    void deleteOrderItem(Long id);
}
