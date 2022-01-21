package numble.daangnservice.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileNameGenerator {

    public static String profileImageDisk(Long id, String fileFormat){
        return ImageUrl.PROFILE_SAVE_URL.getUrl() + id + fileFormat;
    }

    public static String profileImageDb(Long id, String fileFormat){
        return ImageUrl.PROFILE_LOAD_URL.getUrl() + id + fileFormat;
    }
}
