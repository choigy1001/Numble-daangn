package numble.daangnservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.daangnservice.domain.product.service.ProductService;
import numble.daangnservice.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        Long userId = (Long)session.getAttribute("LOGIN_USER");

        log.info("product.getImage= {}", productDto.getProductImages());
        log.info("product content= {}", productDto.getContent());
        productService.registerProduct(productDto, userId);

        return "redirect:/main";
    }
}
