package com.example.orderservice.feign;

import com.example.orderservice.dto.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import quan.common.configuration.FeignClientConfiguration;

import static quan.common.constants.FeignConstants.CUSTOMER_SERVICE;
import static quan.common.constants.PathConstants.API_V1_CUSTOMER;

@FeignClient(value = CUSTOMER_SERVICE, configuration = FeignClientConfiguration.class)
public interface CustomerClient {
    @GetMapping(API_V1_CUSTOMER + "/{customerId}")
    ResponseEntity<CustomerResponse> getById(@PathVariable("customerId") Long id);
}
