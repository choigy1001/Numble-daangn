package numble.daangnservice.domain.utils;

import lombok.Getter;

@Getter
public enum ImageUrl {
    PROFILE_SAVE_URL("/Users/choi_keunyoung/IdeaProjects/daangn-service/src/main/resources/static/profile/"),
    PROFILE_LOAD_URL("../profile/"),
    PRODUCT_SAVE_URL("/Users/choi_keunyoung/IdeaProjects/daangn-service/src/main/resources/static/product/"),
    PRODUCT_LOAD_URL("../product/");

    private String url;

    ImageUrl(String url) {
        this.url = url;
    }
}
