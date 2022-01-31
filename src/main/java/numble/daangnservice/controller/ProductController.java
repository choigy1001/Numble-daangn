package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.myInfo.MyInfoService;
import numble.daangnservice.domain.product.ProductEntity;
import numble.daangnservice.domain.product.service.ProductService;
import numble.daangnservice.domain.user.UserEntity;
import numble.daangnservice.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public String registerProduct(@ModelAttribute("productDto") ProductDto.Register productDto, BindingResult bindingResult,
                                  HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("LOGIN_USER");

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
}