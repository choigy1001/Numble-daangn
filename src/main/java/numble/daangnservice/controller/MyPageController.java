package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.myInfo.MyInfoService;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.infrastructure.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MyPageController {

    private final MyInfoService myInfoService;

    @GetMapping("/myInfo")
    public String moveMyPage(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId, Model model) {

        UserEntity user = myInfoService.findUser(userId);

        model.addAttribute("profileImage", user.getProfileImageUrl());
        model.addAttribute("nickname", user.getNickname());

        return "myInfo/myPage";
    }

    @GetMapping("/myInfo/edit")
    public String myInfoEditForm(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId, Model model) {

        UserEntity user = myInfoService.findUser(userId);

        model.addAttribute("profileImage", user.getProfileImageUrl());
        model.addAttribute("nickname", user.getNickname());

        return "myInfo/myInfoEditForm";
    }

    @PostMapping("/myInfo/edit")
    public String myInfoEdit(@RequestParam String newNickname, @RequestParam MultipartFile profileImage,
                             @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId) throws IOException {

        myInfoService.changeInfo(userId, newNickname, profileImage);
        return "redirect:/myInfo";
    }


    @GetMapping("/myInfo/sell")
    public String mySellList(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId, Model model) {

        List<ProductEntity> product = myInfoService.findProduct(userId);
        model.addAttribute("productList", product);

        return "/myInfo/sellPage";
    }

    @GetMapping("/myInfo/like")
    public String myLikeList(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId, Model model) {

        List<ProductEntity> likeProduct = myInfoService.findLikeProduct(userId);
        log.info("likeProduct = {}", likeProduct.get(0).getTitle());
        model.addAttribute("productList", likeProduct);

        return "myInfo/likePage";
    }
}
