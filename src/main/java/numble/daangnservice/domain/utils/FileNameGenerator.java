package numble.daangnservice.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileNameGenerator {

    public static String profileImageDisk(Long id, String fileFormat){
        return ImageUrl.PROFILE_SAVE_URL.getUrl() + id + fileFormat;
    }

    public static String profileImageDb(Long id, String fileFormat){
        return ImageUrl.PROFILE_LOAD_URL.getUrl() + id + fileFormat;
    }

    public static String ProductImageDisk(String fileFormat) {
        return ImageUrl.PRODUCT_SAVE_URL.getUrl() + uniqueNumber() + fileFormat;
    }
    public static String ProductImageDb(String fileName) {
        return ImageUrl.PRODUCT_LOAD_URL.getUrl() + fileName;
    }

    private static String uniqueNumber(){
        return UUID.randomUUID().toString();
    }

}
