package quan.customerservice.feign;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import quan.common.configuration.FeignClientConfiguration;
import quan.common.dto.cart.CartRequest;

import static quan.common.constants.FeignConstants.CART_SERVICE;
import static quan.common.constants.PathConstants.API_V1_CART;

@FeignClient(name = CART_SERVICE, configuration = FeignClientConfiguration.class)
public interface CartClient {
    @PostMapping(value = API_V1_CART)
    ResponseEntity<Object> createCart(@Valid @RequestBody CartRequest cartRequest);
}
