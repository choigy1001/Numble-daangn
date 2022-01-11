package numble.daangnservice.domain.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.daangnservice.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "table_product_image")
@AttributeOverride(name = "id", column = @Column(name = "product_image_id"))
@Entity
public class ProductImageEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @NotNull
    @Column(name = "product_image_url")
    private String productImageUrl;
}
