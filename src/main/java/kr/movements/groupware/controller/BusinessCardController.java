package kr.movements.groupware.controller;

import kr.movements.groupware.domain.Client;
import kr.movements.groupware.dto.ImageDto;
import kr.movements.groupware.service.BusinessCardImageService;
import kr.movements.groupware.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BusinessCardController {
    private final BusinessCardImageService businessCardImageService;
    private final ClientService clientService;

    @GetMapping("/businessCard")
    public String businessCardUpload(Model model) {
        return "businessCardUploadForm";
    }

    @PostMapping("/client/business")
    public String saveWithName(@ModelAttribute ImageDto dto, @RequestPart("front") MultipartFile file1, @RequestPart("rear") MultipartFile file2, Model model) throws IOException {
        Map<String, Object> param = new HashMap<String, Object>();

        byte[] bytes;

        if (file1 != null) {
            try {
                String fileName = file1.getOriginalFilename();
                bytes = file1.getBytes();

                try {
                    Blob blob = new SerialBlob(bytes);
                    param.put("name", dto.getName());
                    param.put("file", blob);
                    param.put("file_byte", bytes);
                    param.put("file_name", fileName);
                    param.put("file_size", blob.length());
                    param.put("type", 0);
                    businessCardImageService.saveImage(param);
                } catch (SerialException e1) {
                    e1.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (file2 != null) {
            try {
                String fileName = file2.getOriginalFilename();
                bytes = file2.getBytes();

                try {
                    Blob blob = new SerialBlob(bytes);
                    param.put("name", dto.getName());
                    param.put("file", blob);
                    param.put("file_byte", bytes);
                    param.put("file_name", fileName);
                    param.put("file_size", blob.length());
                    param.put("type", 1);
                    businessCardImageService.saveImage(param);
                } catch (SerialException e1) {
                    e1.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Optional<Client> clientOptional = clientService.findByName(dto.getName());
        if (clientOptional.stream().count() > 0) {
            clientService.updateImage(businessCardImageService.findByAllClientId(clientOptional.get().getId()), clientOptional.get().getId());
        }

        return "redirect:/";
    }
}
