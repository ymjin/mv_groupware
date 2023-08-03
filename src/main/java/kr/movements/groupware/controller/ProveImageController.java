package kr.movements.groupware.controller;

import kr.movements.groupware.dto.ImageDto;
import kr.movements.groupware.service.EmployeeService;
import kr.movements.groupware.service.ProveImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProveImageController {
    private final ProveImageService proveImageService;
    private final EmployeeService employeeService;
    @GetMapping("/image")
    public String uploadForm() {
        return "imageUploadForm";
    }

//    @PostMapping("/check/name")
//    public String check(Model model) throws Exception { // /get/member로 호출 시 가상의 memberList를 작성하여 html에 전달

//        return employeeService.findOne("").stream().count() > 0 ? "정상" : "비정상";
//        Map<Integer, String> memberList = new HashMap<>(); // <회원번호, 회원이름>으로 구성된 가상의 멤버 리스트
//        memberList.put(1, "킴오리");
//        memberList.put(10, "킴엔탈");
//        memberList.put(20, "리오리");
//        memberList.put(200, "리엔탈");
//
//        model.addAttribute("memberList", memberList);
//        return "index :: memberTable"; // template 파일 이름 + '::' + 데이터가 변경 될 fragment id

//    }

    @PostMapping("/image/read")
    public String imageToByteArray(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        Map<String, Object> param = new HashMap<String, Object>();

        String fileName = file.getOriginalFilename();
        byte[] bytes;

        try {
            bytes = file.getBytes();

            try {
                Blob blob = new SerialBlob(bytes);
                param.put("file", blob);
                param.put("file_byte", bytes);
                param.put("file_name", fileName);
                param.put("file_size", blob.length());
                proveImageService.saveImage(param);
            } catch (SerialException e1) {
                e1.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @PostMapping("/image/upload")
    public String saveWithName(@ModelAttribute ImageDto dto, @RequestPart("file") MultipartFile file, Model model) throws IOException {
        Map<String, Object> param = new HashMap<String, Object>();

        String fileName = file.getOriginalFilename();
        byte[] bytes;

        try {
            bytes = file.getBytes();

            try {
                Blob blob = new SerialBlob(bytes);
                param.put("name", dto.getName());
                param.put("file", blob);
                param.put("file_byte", bytes);
                param.put("file_name", fileName);
                param.put("file_size", blob.length());
                proveImageService.saveImage(param);
            } catch (SerialException e1) {
                e1.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
