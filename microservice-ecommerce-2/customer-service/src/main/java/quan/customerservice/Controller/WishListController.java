package quan.customerservice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quan.customerservice.dto.request.WishlistRequest;
import quan.customerservice.dto.response.WishlistResponse;
import quan.customerservice.mapper.WishListMapper;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlist")
public class WishListController {
    private final WishListMapper wishlistMapper;
    @PostMapping
    public ResponseEntity<WishlistResponse> create(@RequestBody WishlistRequest wishlistRequest) {
        return ResponseEntity.ok(wishlistMapper.create(wishlistRequest));
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<WishlistResponse>> getAllByCustomerId(@PathVariable("customerId") Long customerId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(wishlistMapper.getAllByCustomerId(customerId, pageable));
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> deleteById(@PathVariable("wishlistId") Long id) {
        wishlistMapper.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
