package kr.movements.groupware.controller;

import kr.movements.groupware.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public String clientList(Model model) {
        model.addAttribute("datas", employeeService.findAll());
        return "employeeList";
    }
}
