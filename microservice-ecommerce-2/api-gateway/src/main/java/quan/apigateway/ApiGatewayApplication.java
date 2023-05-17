package quan.apigateway;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    private static final Logger logger = LogManager.getLogger(ApiGatewayApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean // Đánh dấu phương thức này là một phương thức tạo bean trong Spring Framework
    @Lazy(false) // Đánh dấu bean này không được khởi tạo theo cách lazy
    public List<GroupedOpenApi> apis(RouteDefinitionLocator locator) { // Khai báo phương thức apis với tham số đầu vào là một RouteDefinitionLocator
        List<GroupedOpenApi> groups = new ArrayList<>(); // Khởi tạo một danh sách trống để lưu trữ các nhóm GroupedOpenApi
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block(); // Lấy danh sách các định nghĩa tuyến đường từ RouteDefinitionLocator
        for (RouteDefinition definition : definitions) { // Duyệt qua từng định nghĩa tuyến đường
            logger.info("id: " + definition.getId() + "  " + definition.getUri().toString()); // Ghi thông tin id và uri của định nghĩa tuyến đường vào logger
        }
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")) // Lọc các định nghĩa tuyến đường có id kết thúc bằng "-service"
                .forEach(routeDefinition -> { // Duyệt qua từng định nghĩa tuyến đường được lọc
                    String name = routeDefinition.getId().replaceAll("-service", ""); // Loại bỏ hậu tố "-service" khỏi id để lấy tên nhóm
                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build(); // Xây dựng một nhóm GroupedOpenApi với tên và đường dẫn phù hợp
                });
        return groups; // Trả về danh sách các nhóm GroupedOpenApi
    }
}
