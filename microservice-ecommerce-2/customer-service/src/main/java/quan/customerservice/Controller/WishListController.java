package quan.customerservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a new item for wishlist", description = "Create a new item for wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Invalid customer data"),
            @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    public ResponseEntity<WishlistResponse> create(@RequestBody WishlistRequest wishlistRequest) {
        return ResponseEntity.ok(wishlistMapper.create(wishlistRequest));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get a customer wishlist item", description = "Returns a wishlist items by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved wishlist item"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    public ResponseEntity<Page<WishlistResponse>> getAllByCustomerId(@PathVariable("customerId") Long customerId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(wishlistMapper.getAllByCustomerId(customerId, pageable));
    }

    @DeleteMapping("/{wishlistId}")
    @Operation(summary = "Delete a wishlist item by id", description = "Delete a wishlist item by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Wishlist item deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    public ResponseEntity<Void> deleteById(@PathVariable("wishlistId") Long id) {
        wishlistMapper.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
