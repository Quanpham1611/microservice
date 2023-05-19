package quan.authservice.dto.response;

import lombok.Data;
import quan.authservice.enums.CustomerRole;

import java.time.LocalDate;

@Data
public class CustomerResponse {
    private Long id;
    private String username;
    private String password;
    private CustomerRole role;
    private String firstname;
    private String lastname;
    private String imageUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
