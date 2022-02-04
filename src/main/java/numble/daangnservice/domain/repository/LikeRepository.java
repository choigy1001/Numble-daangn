package numble.daangnservice.domain.repository;

import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.LikeEntity;
import numble.daangnservice.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    List<ProductEntity> findByUserEntity(UserEntity user);
}
