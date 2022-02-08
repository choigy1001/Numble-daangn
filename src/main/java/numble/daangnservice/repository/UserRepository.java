package numble.daangnservice.repository;

import numble.daangnservice.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    UserEntity findByNickname(String nickname);

    Optional<UserEntity> findById(Long id);

}