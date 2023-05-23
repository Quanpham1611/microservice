package com.example.cartservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import quan.common.dto.catalog.product.ProductResponse;

import static quan.common.constants.FeignConstants.CATALOG_SERVICE;
import static quan.common.constants.PathConstants.API_V1_PRODUCT;

@FeignClient(name = CATALOG_SERVICE, configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface ProductClient {
    @GetMapping(value = API_V1_PRODUCT + "/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId);
}
