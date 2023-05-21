package quan.customerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quan.customerservice.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    Page<WishList> findAllByCustomer_Id(Long id, Pageable pageable);
}
