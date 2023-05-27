package com.example.orderservice.service.impl;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quan.common.utils.UpdatingUtil;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<OrderItem> getOrderItemsByOrder(Long orderId, Pageable pageable) {
        return orderItemRepository.findByOrder_Id(orderId, pageable);
    }

    @Override
    public Page<Order> getOrdersByCustomer(Long customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order order = getOrderById(orderId);
        BeanUtils.copyProperties(updatedOrder, order, UpdatingUtil.getNullPropertyNames(order));
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
