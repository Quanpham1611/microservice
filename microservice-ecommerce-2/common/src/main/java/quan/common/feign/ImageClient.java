package quan.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static quan.common.constants.FeignConstants.IMAGE_SERVICE;
import static quan.common.constants.PathConstants.API_V1_IMAGE;

@FeignClient(name = IMAGE_SERVICE, configuration = FeignClientsConfiguration.class)
public interface ImageClient {
    @PostMapping(value=API_V1_IMAGE + "/upload",consumes = {"multipart/form-data"})
    String uploadImage(@RequestPart("file") MultipartFile file);
}
/*
Feign là một thư viện Java giúp bạn viết mã gọi HTTP dễ dàng
hơn bằng cách cung cấp một cấu trúc khai báo cho các yêu cầu
HTTP. Nó cho phép bạn định nghĩa các giao diện Java với các
phương thức tương ứng với các yêu cầu HTTP và sử dụng chú thích
để định nghĩa các chi tiết của yêu cầu như URL, phương thức HTTP
và tham số. Feign sau đó sẽ tạo ra một triển khai cho giao diện
này và tự động thực hiện các yêu cầu HTTP khi bạn gọi các phương
thức của giao diện. Điều này giúp viết mã gọi HTTP trở nên dễ
dàng hơn và giúp mã của bạn dễ đọc và bảo trì hơn.
 */
