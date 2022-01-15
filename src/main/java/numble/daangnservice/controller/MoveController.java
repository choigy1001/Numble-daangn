package numble.daangnservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoveController {

    @GetMapping("/")
    public String moveStart() {
        return "start";
    }

    @GetMapping("/start")
    public String completeLogin() {
        return "start";
    }

}
