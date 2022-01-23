package numble.daangnservice.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.product.ProductCategory;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.product.ProductImageEntity;
import numble.daangnservice.domain.product.ProductStatus;
import numble.daangnservice.domain.repository.ProductImageRepository;
import numble.daangnservice.domain.repository.ProductRepository;
import numble.daangnservice.domain.repository.UserRepository;
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


    @Transactional
    public void registerProduct(ProductDto.Register productDto, Long id) throws IOException {

        Optional<UserEntity> userEntity = userRepository.findById(id);

        ProductEntity productEntity = productRepository.save(
                ProductEntity.builder()
                        .userEntity(userEntity.get())
                        .title(productDto.getTitle())
                        .place("하단2동")
                        .price(productDto.getPrice())
                        .status(ProductStatus.SELL)
                        .category(ProductCategory.findCategory(productDto.getCategory()))
                        .commentCount(0)
                        .likeCount(0)
                        .build()
        );

        List<MultipartFile> productImagesList = productDto.getProductImages();
        List<String> productImageUrlsList = uploadService.uploadAll(productImagesList);

        for (String productImageUrl : productImageUrlsList) {
            productImageRepository.save(
                    ProductImageEntity.builder()
                            .productEntity(productEntity)
                            .productImageUrl(productImageUrl)
                            .build()
            );
        }

    }
}
