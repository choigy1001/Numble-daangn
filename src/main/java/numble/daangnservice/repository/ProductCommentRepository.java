package numble.daangnservice.repository;

import numble.daangnservice.domain.product.ProductCommentEntity;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductCommentEntity, Long> {
    List<ProductCommentEntity> findByProductEntity(ProductEntity productEntity);
}
