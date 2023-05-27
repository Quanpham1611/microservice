package quan.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quan.catalogservice.entity.Banner;
@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
}
