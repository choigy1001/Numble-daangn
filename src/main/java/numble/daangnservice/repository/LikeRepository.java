package numble.daangnservice.repository;

import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.LikeEntity;
import numble.daangnservice.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    List<LikeEntity> findByUserEntity(UserEntity user);

    //다른 방법을 찾는게 매우 시급
    @Query("select p from ProductEntity p, LikeEntity l where p.id = l.productEntity.id and l.id = :likeId")
    ProductEntity findByLikeEntity(@Param("likeId")Long likeId);


}
