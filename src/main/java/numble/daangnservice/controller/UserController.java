package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.user.service.UserService;
import numble.daangnservice.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup/add")
    public String moveSignupForm(Model model) {
        model.addAttribute("signupDto", new UserDto.SIGNUP());
        return "/signup/signUpForm";
    }

    @PostMapping("/signup/add")
    public String serviceSignUp(@Valid @ModelAttribute UserDto.SIGNUP signupDto) {

        userService.signUp(signupDto);
        return "redirect:/signup/complete";
    }

    @GetMapping("/login")
    public String moveLoginForm(Model model) {
        model.addAttribute("loginDto", new UserDto.LOGIN());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String homePageLogin(@Valid @ModelAttribute UserDto.LOGIN loginDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Boolean findUser = userService.login(loginDto);
        if (!findUser) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호 오류입니다");
            return "login/loginForm";
        }
        return "/main";
    }

    @GetMapping("/signup/complete")
    public String signUpComplete() {
        return "/signup/complete";
    }


}
