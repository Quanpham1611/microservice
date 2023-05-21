package quan.customerservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import quan.customerservice.dto.request.WishlistRequest;
import quan.customerservice.entity.WishList;

public interface WishlistService {
    WishList create(WishlistRequest wishlist);
    Page<WishList> getAllByCustomerId(Long id, Pageable pageable);
    void delete(Long id);
}
