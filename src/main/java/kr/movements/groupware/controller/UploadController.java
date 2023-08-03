package kr.movements.groupware.controller;

import kr.movements.groupware.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UploadController {
    private final EmployeeService employeeService;
    private final TeamService teamService;
    private final GradeService gradeService;
    private final ClientService clientService;
    private final CompanyService companyService;
    private final WorkService workService;
    private final StatusService statusService;
    private final LocationService locationService;

    @GetMapping("/upload")
    public String uploadForm() {
        return "uploadForm";
    }

    @PostMapping("/excel/all")
    public String readExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException(("엑셀파일만 업로드 해주세요"));
        }

        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        teamService.parsing(workbook.getSheetAt(workbook.getSheetIndex("team")));
        statusService.parsing(workbook.getSheetAt(workbook.getSheetIndex("status")));
        workService.parsing(workbook.getSheetAt(workbook.getSheetIndex("work")));
        companyService.parsing(workbook.getSheetAt(workbook.getSheetIndex("company")));
        gradeService.parsing(workbook.getSheetAt(workbook.getSheetIndex("grade")));
        clientService.parsing(workbook.getSheetAt(workbook.getSheetIndex("client")));

        employeeService.parsing(workbook.getSheetAt(workbook.getSheetIndex("employee")));

        model.addAttribute("result", "true");
        return "redirect:/";
    }

    @PostMapping("/excel/employee")
    public String readEmployee(@RequestParam("file") MultipartFile file, Model model) throws IOException {
//        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

//        if (!extension.equals("xlsx") && !extension.equals("xls")) {
//            throw new IOException(("엑셀파일만 업로드 해주세요"));
//        }

        Workbook workbook = null;
//        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
//        } else if (extension.equals("xls")) {
//            workbook = new HSSFWorkbook(file.getInputStream());
//        }

        //employeeService.parsing(workbook.getSheetAt(workbook.getSheetIndex("employee")));
        employeeService.parsing(workbook.getSheetAt(0));
        model.addAttribute("result", "true");
        return "redirect:/";
    }

    @PostMapping("/excel/location")
    public String readLocation(@RequestParam("file") MultipartFile file, Model model) throws IOException {
//        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

//        if (!extension.equals("xlsx") && !extension.equals("xls")) {
//            throw new IOException(("엑셀파일만 업로드 해주세요"));
//        }

        Workbook workbook = null;
//        if (extension.equals("xlsx")) {
        workbook = new XSSFWorkbook(file.getInputStream());
//        } else if (extension.equals("xls")) {
//            workbook = new HSSFWorkbook(file.getInputStream());
//        }

        //employeeService.parsing(workbook.getSheetAt(workbook.getSheetIndex("employee")));
        locationService.parsing(workbook.getSheetAt(0));

        model.addAttribute("result", "true");
        return "redirect:/";
    }

    @PostMapping("/excel/client")
    public String readClient(@RequestParam("file") MultipartFile file, Model model) throws IOException {
//        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

//        if (!extension.equals("xlsx") && !extension.equals("xls")) {
//            throw new IOException(("엑셀파일만 업로드 해주세요"));
//        }

        Workbook workbook = null;
//        if (extension.equals("xlsx")) {
        workbook = new XSSFWorkbook(file.getInputStream());
//        } else if (extension.equals("xls")) {
//            workbook = new HSSFWorkbook(file.getInputStream());
//        }

        clientService.parsing(workbook.getSheetAt(0));
        model.addAttribute("result", "true");
        return "redirect:/";
    }
}
