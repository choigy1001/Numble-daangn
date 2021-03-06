package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.myInfo.MyInfoService;
import numble.daangnservice.domain.product.ProductCommentEntity;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.product.service.ProductService;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.domain.user.service.UserService;
import numble.daangnservice.dto.CommentDto;
import numble.daangnservice.dto.ProductDto;
import numble.daangnservice.infrastructure.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/form")
    public String moveProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto.Register());
        return "/product/writeForm";
    }

    @PostMapping("/product/form")
    public String registerProduct(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId,
                                  @ModelAttribute("productDto") ProductDto.Register productDto) throws IOException {

        productService.registerProduct(productDto, userId);
        return "redirect:/main";
    }

    @GetMapping("/product/page/{productId}")
    public String moveProductPage(@PathVariable Long productId, Model model) {

        ProductEntity product = productService.findProduct(productId);
        UserEntity userEntity = product.getUserEntity();

        model.addAttribute("product", product);
        model.addAttribute("nickname", userEntity.getNickname());

        return "/product/productPage";
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        productService.deleteProductInfo(productId);
        return "redirect:/myInfo/sell";
    }

    @GetMapping("/product/like/{productId}")
    public String likeProduct(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId,
                              @PathVariable Long productId) {
        productService.saveUserLikeProduct(productId, userId);
        return "redirect:/product/page/{productId}";
    }

    @GetMapping("/product/reserve/{productId}")
    public String changeStatusReserve(@PathVariable Long productId) {
        productService.changeToReserve(productId);
        return "redirect:/myInfo";
    }

    @GetMapping("/product/complete/{productId}")
    public String changeStatusComplete(@PathVariable Long productId){
        productService.changeToComplete(productId);
        return "redirect:/myInfo";
    }

    @GetMapping("/product/comment/{productId}")
    public String viewProductComment(@PathVariable Long productId, Model model) {
        ProductEntity product = productService.findProduct(productId);
        List<CommentDto> productComment = productService.getProductComment(productId);

        //??????????????? ????????? DTO??? ????????? ??????????????? ????????? ?????? ??????
        // ????????? ????????? ??????????????? ????????? ????????? ???????????? ??????

        model.addAttribute("product",product);
        model.addAttribute("productComment", productComment);
        return "/product/productComment";
    }

    //product ?????? ????????? ????????? ?????? ??????????????? ??????
    @GetMapping("/product/comment/write/{productId}")
    public String moveWriteCommentForm(@PathVariable Long productId, Model model){
        ProductEntity product = productService.findProduct(productId);
        model.addAttribute("product", product);
        return "/product/writeCommentPage";
    }

    @PostMapping("/product/comment/write")
    public String writeComment(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Long userId,
                               @RequestParam String comment, @RequestParam Long productId, RedirectAttributes redirectAttributes) {
        productService.saveComment(userId, productId, comment);
        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/product/page/{productId}";
    }
}
