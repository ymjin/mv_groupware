package kr.movements.groupware.controller;

import kr.movements.groupware.dto.ClientDto;
import kr.movements.groupware.service.BusinessCardImageService;
import kr.movements.groupware.service.ClientService;
import kr.movements.groupware.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final CompanyService companyService;
    private final BusinessCardImageService businessCardImageService;

    @GetMapping("/client")
    public String clientList(Model model) {
        List<ClientDto> list = companyService.setCompanyName(clientService.findAll());
        list = businessCardImageService.setImage(list);

        model.addAttribute("datas", list);
        return "clientList";
    }
}
