package numble.daangnservice.domain.product;

import lombok.Getter;

@Getter
public enum ProductStatus {

    SELL("판매중"), RESERVATION("예약중"), COMPLETE("거래완료");

    private String status;

    ProductStatus(String status) {
        this.status = status;
    }
}
