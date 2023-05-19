package quan.authservice.feign;

import org.bouncycastle.asn1.cmp.PKIMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import quan.authservice.dto.response.CustomerResponse;

import static quan.common.constants.FeignConstants.CUSTOMER_SERVICE;
import static quan.common.constants.PathConstants.API_V1_CUSTOMER;

@FeignClient(name = CUSTOMER_SERVICE, configuration = FeignClientsConfiguration.class)
public interface CustomerClient {
    @GetMapping(API_V1_CUSTOMER + "/username/{username}")
    ResponseEntity<CustomerResponse> getByUserName(@PathVariable("username") String username);

//    PKIMessage getByUsername(String );
}
