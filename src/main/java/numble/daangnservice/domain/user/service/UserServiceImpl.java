package numble.daangnservice.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.repository.UserRepository;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.dto.UserDto;
import numble.daangnservice.exception.BusinessException;
import numble.daangnservice.exception.ErrorCode;
import numble.daangnservice.exception.UnknownUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public boolean signUp(UserDto.SIGNUP signupDto) {

        Optional<UserEntity> findUserEntity = userRepository.findByEmail(signupDto.getEmail());

        if(findUserEntity.isPresent()){
            return false;
        }

        userRepository.save(
                UserEntity.builder()
                        .email(signupDto.getEmail())
                        .password(signupDto.getPassword())
                        .username(signupDto.getUsername())
                        .phoneNumber(signupDto.getPhoneNumber())
                        .nickname(signupDto.getNickname())
                        .profileImageUrl("ImageUrl")
                        .build()
        );
        return true;
    }

    @Override
    public UserEntity login(UserDto.LOGIN loginDto) {

        UserEntity findUser = userRepository.findByEmail(loginDto.getEmail())
                .filter(u -> u.getPassword().equals(loginDto.getPassword()))
                .orElse(null);
//        log.info("findUser email={}",findUser.getEmail());
//        log.info("findUser password={}",findUser.getPassword());

        return findUser;

        /*
        if(Boolean.FALSE.equals(findUserEntity.isPresent())){
            throw new UnknownUserException(ErrorCode.NOT_REGISTERED_USER);
        }*/

    }
}
