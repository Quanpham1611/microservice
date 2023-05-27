package quan.catalogservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import quan.common.configuration.FeignClientConfiguration;

import static quan.common.constants.FeignConstants.CUSTOMER_SERVICE;
import static quan.common.constants.PathConstants.API_V1_CUSTOMER;

@FeignClient(name = CUSTOMER_SERVICE,configuration = FeignClientConfiguration.class)
public interface CustomerClient {
    @GetMapping(value=API_V1_CUSTOMER + "/{id}")
    ResponseEntity<Object> getById(@PathVariable("id") Long id);
}
