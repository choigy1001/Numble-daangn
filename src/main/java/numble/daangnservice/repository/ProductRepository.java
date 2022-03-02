package numble.daangnservice.repository;

import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByUserEntity(UserEntity userEntity);

    Optional<ProductEntity> findById(Long id);

    void deleteById(Long id);

}
