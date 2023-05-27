package com.example.orderservice.mapper;

import com.example.orderservice.dto.request.OrderRequest;
import com.example.orderservice.dto.response.OrderItemResponse;
import com.example.orderservice.dto.response.OrderResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.service.OrderItemService;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import quan.common.dto.cart.CartItemResponse;
import quan.common.mapper.BasicMapper;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final BasicMapper basicMapper;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    public OrderResponse create(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderDescription(orderRequest.getOrderDescription());
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setCustomerId(orderRequest.getCustomerId());
        for(CartItemResponse cartItemResponse : orderRequest.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItemResponse.getQuantity());
            orderItem.setProductId(cartItemResponse.getProductId());
            orderItem.setPrice(cartItemResponse.getPrice());
            order.increaseTotalPrice(cartItemResponse.getPrice());
            order.addItem(orderItemService.create(orderItem));
        }
        return basicMapper.convertTo(orderService.create(order), OrderResponse.class);
    }
    public Page<OrderResponse> getAll(Pageable pageable) {
        return orderService.getAll(pageable).map(order -> basicMapper.convertTo(order, OrderResponse.class));
    }
    public OrderResponse getOrderById(Long id) {
        return basicMapper.convertTo(orderService.getOrderById(id), OrderResponse.class);
    }
    public Page<OrderItemResponse> getOrderItemsByOrder(Long orderId, Pageable pageable) {
        return orderService.getOrderItemsByOrder(orderId, pageable)
                .map(orderItem -> basicMapper.convertTo(orderItem, OrderItemResponse.class));
    }
    public Page<OrderResponse> getOrdersByCustomer(Long customerId, Pageable pageable) {
        return orderService.getOrdersByCustomer(customerId, pageable).map(order -> basicMapper.convertTo(order, OrderResponse.class));
    }
    public OrderResponse updateOrder(Long id, OrderRequest updatedOrder) {
        Order order = basicMapper.convertTo(updatedOrder, Order.class);
        return basicMapper.convertTo(orderService.updateOrder(id, order), OrderResponse.class);
    }
    public void deleteOrder(Long id) {
        orderService.deleteOrder(id);
    }
}
