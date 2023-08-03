package kr.movements.groupware.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class GroupwareController {

    @GetMapping("/")
    public String main() {
        return "home";
    }
}
