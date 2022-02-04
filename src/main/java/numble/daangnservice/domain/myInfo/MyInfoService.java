package numble.daangnservice.domain.myInfo;

import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MyInfoService {

    void changeInfo(Long userId, String newNickname, MultipartFile profileImageUrl) throws IOException;

    void deleteProfileImage();

    UserEntity findUser(Long id);

    List<ProductEntity> findProduct(UserEntity userEntity);

    List<ProductEntity> findLikeProduct(Long userId);
}
