package numble.daangnservice.domain.myInfo;

import numble.daangnservice.domain.user.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MyInfoService {

    void changeInfo(Long userId, String newNickname, MultipartFile profileImageUrl) throws IOException;

    void deleteProfileImage();

    UserEntity findUser(Long id);
}
