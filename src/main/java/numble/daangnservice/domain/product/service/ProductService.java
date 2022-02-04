package numble.daangnservice.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.product.ProductCategory;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.product.ProductImageEntity;
import numble.daangnservice.domain.product.ProductStatus;
import numble.daangnservice.domain.repository.LikeRepository;
import numble.daangnservice.domain.repository.ProductImageRepository;
import numble.daangnservice.domain.repository.ProductRepository;
import numble.daangnservice.domain.repository.UserRepository;
import numble.daangnservice.domain.user.LikeEntity;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.domain.utils.UploadService;
import numble.daangnservice.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UploadService uploadService;
    private final LikeRepository likeRepository;


    @Transactional
    public void registerProduct(ProductDto.Register productDto, Long id) throws IOException {

        Optional<UserEntity> userEntity = userRepository.findById(id);

        List<MultipartFile> productImagesList = productDto.getProductImages();
        List<String> productImageUrlsList = uploadService.uploadAll(productImagesList);
        String firstImage = productImageUrlsList.get(0);

        ProductEntity productEntity = productRepository.save(
                ProductEntity.builder()
                        .userEntity(userEntity.get())
                        .title(productDto.getTitle())
                        .place("하단2동")
                        .price(productDto.getPrice())
                        .status(ProductStatus.SELL)
                        .content(productDto.getContent())
                        .category(ProductCategory.findCategory(productDto.getCategory()))
                        .commentCount(0)
                        .likeCount(0)
                        .representativeImage(firstImage)
                        .build()
        );


        for (String productImageUrl : productImageUrlsList) {
            productImageRepository.save(
                    ProductImageEntity.builder()
                            .productEntity(productEntity)
                            .productImageUrl(productImageUrl)
                            .build()
            );
        }

    }

    public ProductEntity findProduct(Long productId){
        return productRepository.findById(productId).get();
    }

    @Transactional
    public void deleteProductInfo(Long productId){
        ProductEntity product = findProduct(productId);
        productRepository.deleteById(productId);
        productImageRepository.deleteByProductEntity(product);
    }

    @Transactional
    public void saveUserLikeProduct(Long productId, Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        Optional<ProductEntity> productEntity= productRepository.findById(productId);

        likeRepository.save(
                LikeEntity.builder().
                        userEntity(userEntity.get()).
                        productEntity(productEntity.get()).
                        build()
        );

    }

    @Transactional
    public void changeToReserve(Long productId) {
        ProductEntity product = findProduct(productId);
        product.editStatus(ProductStatus.RESERVATION);
    }

    @Transactional
    public void changeToComplete(Long productId) {
        ProductEntity product = findProduct(productId);
        product.editStatus(ProductStatus.COMPLETE);
    }
}
