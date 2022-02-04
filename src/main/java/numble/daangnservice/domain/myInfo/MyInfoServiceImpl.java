package numble.daangnservice.domain.myInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.repository.LikeRepository;
import numble.daangnservice.domain.repository.ProductRepository;
import numble.daangnservice.domain.repository.UserRepository;
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
public class MyInfoServiceImpl implements MyInfoService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LikeRepository likeRepository;

    @Transactional
    @Override
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

    @Override
    public void deleteProfileImage() {

    }

    @Override
    public UserEntity findUser(Long id) {
        Optional<UserEntity> findUser = userRepository.findById(id);
        return findUser.get();
    }

    @Override
    public List<ProductEntity> findProduct(UserEntity userEntity) {
        List<ProductEntity> productEntityList = new ArrayList<>();

        List<ProductEntity> byUserEntity = productRepository.findByUserEntity(userEntity);
//        for (ProductEntity productEntity : byUserEntity) {
//            productEntityList.add(productEntity);
//        }
        productEntityList.addAll(byUserEntity);

        return productEntityList;
    }

    @Override
    public List<ProductEntity> findLikeProduct(Long userId) {
        UserEntity user = findUser(userId);

        List<ProductEntity> productEntityList = new ArrayList<>();

        List<ProductEntity> productList = likeRepository.findByUserEntity(user);

        return productList;
    }
}
