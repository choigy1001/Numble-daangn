package numble.daangnservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SIGNUP{

        @Email
        @NotNull
        private String email;
        @NotNull
        private String password;
        @NotNull
        private String username;
        @NotNull
        private String phoneNumber;
        @NotNull
        private String nickname;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LOGIN{

        @NotNull
        private String email;

        @NotNull
        private String password;
    }
}
