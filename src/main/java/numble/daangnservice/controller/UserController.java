package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.domain.user.service.UserService;
import numble.daangnservice.dto.UserDto;
import numble.daangnservice.infrastructure.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String moveSignupForm(Model model) {
        model.addAttribute("signupDto", new UserDto.SIGNUP());
        return "/signup/signUpForm";
    }

    @PostMapping("/signup")
    public String serviceSignUp(@Valid @ModelAttribute("signupDto") UserDto.SIGNUP signupDto, BindingResult bindingResult) {
        boolean user = userService.signUp(signupDto);
        if(!user){
            bindingResult.reject("회원가입 실패", "중복된 회원입니다. 다른 이메일 사용해주세요");
            return "/signup/signUpForm";
        }
        return "redirect:/signup/complete";
    }

    @GetMapping("/login")
    public String moveLoginForm(@ModelAttribute("loginDto") UserDto.LOGIN loginDto) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String homePageLogin(@Valid @ModelAttribute("loginDto") UserDto.LOGIN loginDto, BindingResult bindingResult,
                                @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        UserEntity findUser = userService.login(loginDto);
        if (findUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류입니다");
            return "login/loginForm";
        }
        //로그인 성공 처리
        HttpSession session = request.getSession();
        //세션 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, findUser);

        return "redirect:" + redirectURL;
//        return "/main";
    }

    @GetMapping("/signup/complete")
    public String signUpComplete() {
        return "/signup/complete";
    }


}
