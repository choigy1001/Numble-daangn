package numble.daangnservice.domain.user.service;

import numble.daangnservice.dto.UserDto;

public interface UserService {

    void signUp(UserDto.SIGNUP signupDto);

    Boolean login(UserDto.LOGIN loginDto);
}
