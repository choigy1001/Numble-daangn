package numble.daangnservice.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.repository.UserRepository;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
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
                        .profileImageUrl("../profile/basicImage.png")
                        .build()
        );
        return true;
    }

    public UserEntity login(UserDto.LOGIN loginDto) {
        return userRepository.findByEmail(loginDto.getEmail())
                .filter(u -> u.getPassword().equals(loginDto.getPassword()))
                .orElse(null);
    }
}
