package numble.daangnservice.domain.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class UploadService {

    public String upload(MultipartFile Image) throws IOException {
        String fileFormat = getFileFormat(Image.getOriginalFilename());
        String ProductImageDiskUrl = FileNameGenerator.ProductImageDisk(fileFormat);
        Image.transferTo(new File(ProductImageDiskUrl));

        //UUID로 생성된 파일명 때문에 파싱해서 사용
        String GeneratedFilename = ProductImageDiskUrl.replace(ImageUrl.PRODUCT_SAVE_URL.getUrl(), "");

        return FileNameGenerator.ProductImageDb(GeneratedFilename);
    }
    public List<String> uploadAll(List<MultipartFile> productImagesList) throws IOException {
        List<String> productImageUrlsList = new ArrayList<>();

        for (MultipartFile productImage : productImagesList) {
            productImageUrlsList.add(upload(productImage));
        }

        return productImageUrlsList;
    }

    private String getFileFormat(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
