package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.myInfo.MyInfoService;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MyPageController {

    private final MyInfoService myInfoService;

    @GetMapping("/myInfo")
    public String moveMyPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        UserEntity user = myInfoService.findUser(userId);

        model.addAttribute("profileImage", user.getProfileImageUrl());
        model.addAttribute("nickname", user.getNickname());

        return "myInfo/myPage";
    }

    @GetMapping("/myInfo/edit")
    public String myInfoEditForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        UserEntity user = myInfoService.findUser(userId);

        model.addAttribute("profileImage", user.getProfileImageUrl());
        model.addAttribute("nickname", user.getNickname());
        log.info("profileImageUrl={}", user.getProfileImageUrl());
        return "myInfo/myInfoEditForm";
    }

    @PostMapping("/myInfo/edit")
    public String myInfoEdit(@RequestParam String newNickname, @RequestParam MultipartFile profileImage,
                             HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");

        log.info("newNickname = {}", newNickname);
        log.info("profileImage={}", profileImage);

        myInfoService.changeInfo(userId, newNickname, profileImage);

        return "redirect:/myInfo";
    }


    @GetMapping("/myInfo/sell")
    public String mySellList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        UserEntity user = myInfoService.findUser(userId);
        List<ProductEntity> product = myInfoService.findProduct(user);

        model.addAttribute("productList", product);

        return "/myInfo/sellPage";
    }

    @GetMapping("/myInfo/like")
    public String myLikeList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");

        List<ProductEntity> likeProduct = myInfoService.findLikeProduct(userId);
        model.addAttribute("productList", likeProduct);

        return "myInfo/likePage";
    }
}
