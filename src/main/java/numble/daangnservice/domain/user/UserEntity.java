package numble.daangnservice.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.daangnservice.domain.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "table_user"
)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Entity
public class UserEntity extends BaseEntity {

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Column(name = "profile_image_url")
    private String profileImageUrl;


    public void editInfo(String newNickname, String newProfileImageUrl) {
        this.nickname = newNickname;
        this.profileImageUrl = newProfileImageUrl;
    }

    @Builder
    public UserEntity(
            String email,
            String password,
            String username,
            String phoneNumber,
            String nickname,
            String profileImageUrl){

        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
