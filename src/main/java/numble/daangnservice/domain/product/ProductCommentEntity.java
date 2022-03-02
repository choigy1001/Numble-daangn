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
@Table(name = "table_product_comment")
@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
@Entity
public class ProductCommentEntity extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @NotNull
    @Column(name = "content")
    private String content;


    @Builder
    public ProductCommentEntity(
            UserEntity userEntity,
            ProductEntity productEntity,
            String content) {
        this.userEntity = userEntity;
        this.productEntity = productEntity;
        this.content = content;
    }
}
