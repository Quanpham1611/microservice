package quan.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quan.customerservice.entity.ShippingAddress;

import java.util.List;

@Repository
public interface ShippingAdressRepository extends JpaRepository<ShippingAddress, Long> {
    List<ShippingAddress> findAllByCustomer_Id(Long customerId);
}
