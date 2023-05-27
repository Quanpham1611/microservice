package com.example.orderservice.mapper;

import com.example.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {
    private final OrderItemService orderItemService;

    public void deleteOrderItem(Long id) {
        orderItemService.deleteOrderItem(id);
    }
}
