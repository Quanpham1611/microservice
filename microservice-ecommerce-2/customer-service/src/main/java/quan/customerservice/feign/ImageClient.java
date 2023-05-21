package quan.customerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import quan.common.configuration.FeignClientConfiguration;

import static quan.common.constants.FeignConstants.IMAGE_SERVICE;
import static quan.common.constants.PathConstants.API_V1_IMAGE;

@FeignClient(name = IMAGE_SERVICE, configuration = FeignClientConfiguration.class)
public interface ImageClient {
    @PostMapping(value = API_V1_IMAGE + "/upload", consumes = {"multipart/form-data"})
    String uploadImage(@RequestPart("file")MultipartFile file);
}
