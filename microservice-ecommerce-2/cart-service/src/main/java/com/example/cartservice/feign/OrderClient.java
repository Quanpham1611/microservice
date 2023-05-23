package com.example.cartservice.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import quan.common.configuration.FeignClientConfiguration;
import quan.common.dto.cart.CartResponse;

import static quan.common.constants.FeignConstants.ORDER_SERVICE;
import static quan.common.constants.PathConstants.API_V1_ORDER;

@FeignClient(name = ORDER_SERVICE, configuration = FeignClientConfiguration.class)
public interface OrderClient {
    @PostMapping(value = API_V1_ORDER)
    ResponseEntity<Object> create(@Valid @RequestBody CartResponse cart);
}
