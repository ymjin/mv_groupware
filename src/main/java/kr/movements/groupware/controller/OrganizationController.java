package kr.movements.groupware.controller;

import kr.movements.groupware.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrganizationController {
    private final TeamService teamService;

    @GetMapping("/organization")
    public String clientList(Model model) {
        model.addAttribute("datas", teamService.findByLevel());
        return "organization";
    }
}
