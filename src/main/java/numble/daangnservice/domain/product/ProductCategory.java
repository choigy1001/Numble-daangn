package numble.daangnservice.domain.product;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductCategory {
    DIGITAL_DEVICE("디지털기기"), HOME_APPLIANCES("생활가전"), FURNITURE("가구/인테리어"), INFANT_CHILD("유아동"),
    LIVING_PROCESSED_FOOD("생활/가공식품"), CHILDREN_BOOK("유아도서"), SPORTS_LEISURE("스포츠/레저"), WOMEN_ACCESSORY("여성잡화"),
    WOMEN_CLOTHING("여성의류"), MEN_FASHION_MISCELLANEOUS_GOOD("남성패션/잡화"), GAME_HOBBY("게임/취미"),
    BEAUTY("뷰티/미용"), PET_SUPPLY("반려동물용품"), BOOK_TICKET_RECORD("도서/티켓/음반"), PLANT("식물"),
    USED_GOOD("기타중고물품"), USED_CARS("중고차");

    private String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public static ProductCategory findCategory(String category) {
        return Arrays.stream(values())
                .filter(productCategory -> productCategory.category.equals(category))
                .findFirst()
                .orElse(null);
    }
}
