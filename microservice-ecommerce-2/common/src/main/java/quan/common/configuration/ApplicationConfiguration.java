package quan.common.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    // This method creates and configures a ModelMapper object
    @Bean
    public ModelMapper modelMapper() {
        // Create a new ModelMapper object
        ModelMapper mapper = new ModelMapper();
        // Configure the ModelMapper object
        mapper.getConfiguration()
                // Set the matching strategy to strict
                .setMatchingStrategy(MatchingStrategies.STRICT)
                // Enable field matching
                .setFieldMatchingEnabled(true)
                // Skip null values during mapping
                .setSkipNullEnabled(true)
                // Set the field access level to private
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        // Return the configured ModelMapper object
        return mapper;
    }
}
/*
Đoạn mã này định nghĩa một phương thức tạo và cấu hình một đối
tượng ModelMapper. Chú thích @Bean cho biết phương thức này được
sử dụng để tạo một bean có thể được quản lý bởi Spring Framework.
Đối tượng ModelMapper được cấu hình với chiến lược khớp chặt chẽ,
kích hoạt khớp trường, bỏ qua các giá trị null và mức truy cập
trường riêng tư. Phương thức trả về đối tượng ModelMapper
đã được cấu hình.
 */
