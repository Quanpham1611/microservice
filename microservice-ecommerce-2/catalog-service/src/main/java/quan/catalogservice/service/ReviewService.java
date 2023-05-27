package quan.catalogservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import quan.catalogservice.entity.Review;

public interface ReviewService {
    Review create(Long productId, Review review);
    Review update(Long reviewId, Long productId, Review review);
    Review getById(Long reviewId);
    Page<Review> getByProductId(Long productId, Pageable pageable);
    Page<Review> getByCustomerId(Long customerId, Pageable pageable);
    void deleteById(Long reviewId);
}
