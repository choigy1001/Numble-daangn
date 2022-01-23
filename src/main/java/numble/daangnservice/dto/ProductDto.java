package numble.daangnservice.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Register{

        @NotNull
        private String title;

        @NotNull
        private String category;

        @NotNull
        private Integer price;

        private List<MultipartFile> productImages;

        @NotNull
        private String content;
    }
}
