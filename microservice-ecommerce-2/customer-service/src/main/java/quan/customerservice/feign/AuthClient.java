package quan.customerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import quan.common.configuration.FeignClientConfiguration;
import quan.common.dto.auth.AuthenticationRequest;

import static quan.common.constants.FeignConstants.AUTH_SERVICE;
import static quan.common.constants.PathConstants.API_V1_AUTH;

@FeignClient(value = AUTH_SERVICE, configuration = FeignClientConfiguration.class)
public interface AuthClient {
    @PostMapping(API_V1_AUTH)
    void authenticate(@RequestBody AuthenticationRequest authenticationRequest);
}
