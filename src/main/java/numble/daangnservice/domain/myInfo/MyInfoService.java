package numble.daangnservice.domain.myInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.LikeEntity;
import numble.daangnservice.repository.LikeRepository;
import numble.daangnservice.repository.ProductRepository;
import numble.daangnservice.repository.UserRepository;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.domain.utils.FileNameGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyInfoService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void changeInfo(Long userId, String newNickname, MultipartFile profileImage) throws IOException {
        Optional<UserEntity> nowUser = userRepository.findById(userId);
        String fileFormat = getFileFormat(profileImage.getOriginalFilename());

        //실제 폴더에 저장하는 경로
        String saveImageDir = FileNameGenerator.profileImageDisk(userId, fileFormat);
        profileImage.transferTo(new File(saveImageDir));

        //이미지를 부르기 위한 DB 저장 경로
        String loadImageDir = FileNameGenerator.profileImageDb(userId, fileFormat);
        nowUser.get().editInfo(newNickname, loadImageDir);

    }

    private String getFileFormat(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    public UserEntity findUser(Long id) {
        Optional<UserEntity> findUser = userRepository.findById(id);
        return findUser.get();
    }

    public List<ProductEntity> findProduct(Long userId) {
        UserEntity userEntity = findUser(userId);
        return productRepository.findByUserEntity(userEntity);
    }

    public List<ProductEntity> findLikeProduct(Long userId) {
        UserEntity user = findUser(userId);
        List<LikeEntity> likeList = likeRepository.findByUserEntity(user);

        List<ProductEntity> productList = new ArrayList<>();
        for (LikeEntity likeEntity : likeList) {
            ProductEntity productEntity = likeRepository.findByLikeEntity(likeEntity.getId());
            productList.add(productEntity);
        }
//        List<LikeEntity> likeList = likeRepository.findByUserEntity(user); //유저 엔티티를 넣어야하는가 아니면 단순 userId를 넣어도 상관없는가

        return productList;
    }
}
