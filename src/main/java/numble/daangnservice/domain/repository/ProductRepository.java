package numble.daangnservice.domain.repository;

import numble.daangnservice.domain.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
