package quan.common.dto.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartResponse {
    private Long id;
    private Long customerId;
    @JsonIgnoreProperties("cart")
    private List<CartItemResponse> cartItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
