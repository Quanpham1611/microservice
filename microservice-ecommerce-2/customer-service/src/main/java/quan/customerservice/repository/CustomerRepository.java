package quan.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quan.customerservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByUsername(String username);

}

