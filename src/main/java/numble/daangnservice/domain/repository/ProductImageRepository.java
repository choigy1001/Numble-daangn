package numble.daangnservice.domain.repository;

import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.product.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
    void deleteByProductEntity(ProductEntity productEntity);
}
