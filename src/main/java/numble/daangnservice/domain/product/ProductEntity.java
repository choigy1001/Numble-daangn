package numble.daangnservice.domain.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.daangnservice.domain.BaseEntity;
import numble.daangnservice.domain.user.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "table_product")
@AttributeOverride(name ="id", column = @Column(name = "product_id"))
@Entity
public class ProductEntity extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "place")
    private String place;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;

    @NotNull
    @Column(name = "comment_count")
    private Integer commentCount = 0;

    @NotNull
    @Column(name = "like_count")
    private Integer likeCount = 0;

    @Builder
    public ProductEntity(UserEntity userEntity,
                         String title,
                         String place,
                         Integer price,
                         ProductStatus status,
                         ProductCategory category,
                         Integer commentCount,
                         Integer likeCount) {
        this.userEntity = userEntity;
        this.title = title;
        this.place = place;
        this.price = price;
        this.status = status;
        this.category = category;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }
}
