package quan.customerservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import quan.common.mapper.BasicMapper;
import quan.customerservice.dto.request.WishlistRequest;
import quan.customerservice.dto.response.WishlistResponse;
import quan.customerservice.service.WishlistService;

@Component
@RequiredArgsConstructor
public class WishListMapper {
    private final BasicMapper basicMapper;
    private final WishlistService wishlistService;

    public WishlistResponse create(WishlistRequest wishlistRequest) {
        return basicMapper.convertTo(wishlistService.create(wishlistRequest), WishlistResponse.class);
    }

    public Page<WishlistResponse> getAllByCustomerId(Long customerId, Pageable pageable) {
        return wishlistService.getAllByCustomerId(customerId, pageable).map(w -> basicMapper.convertTo(w, WishlistResponse.class));
    }

    public void deleteById(Long id) {
        wishlistService.delete(id);
    }
}
