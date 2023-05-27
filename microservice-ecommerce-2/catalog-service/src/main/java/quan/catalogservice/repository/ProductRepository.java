package quan.catalogservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import quan.catalogservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategory_Id(Long id, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p SET p.ordersCount = " +
            "CASE WHEN :increase = true THEN (p.ordersCount + 1) " +
            "ELSE (p.ordersCount - 1) END " +
            "WHERE p.id = :productId")
    void updateOrderCount(@Param("productId") Long productId, @Param("increase") boolean increase);
}
