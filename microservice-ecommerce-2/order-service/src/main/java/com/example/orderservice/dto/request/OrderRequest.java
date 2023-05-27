package com.example.orderservice.dto.request;

import com.example.orderservice.enums.OrderStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;
import quan.common.dto.cart.CartResponse;

@Data
public class OrderRequest extends CartResponse {
    @Size(min = 3, max = 25, message = "Order description must be between 3 and 100 characters")
    private String orderDescription;
    private OrderStatus orderStatus;
}
