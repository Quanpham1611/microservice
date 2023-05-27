package quan.catalogservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quan.catalogservice.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByProduct_Id(Long productId, Pageable pageable);
    Page<Review> findByCustomerId(Long customerId, Pageable pageable);
}
