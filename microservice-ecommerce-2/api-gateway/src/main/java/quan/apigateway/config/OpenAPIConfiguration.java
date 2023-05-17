package quan.apigateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI getWayOpenApi(){
        return new OpenAPI().info(new Info().title("Demofor microservice apis")
                .description("Document for all api")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("Demo of Quan")
                        .email("qpham1611200#@gmail.com")));
    }
}
