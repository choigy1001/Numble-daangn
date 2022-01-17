package numble.daangnservice.domain.user.service;

import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.dto.UserDto;

public interface UserService {

    boolean signUp(UserDto.SIGNUP signupDto);

    UserEntity login(UserDto.LOGIN loginDto);
}
