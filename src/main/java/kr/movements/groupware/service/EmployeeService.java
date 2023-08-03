package kr.movements.groupware.service;

import kr.movements.groupware.dto.EmployeeDto;
import kr.movements.groupware.domain.*;
import kr.movements.groupware.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final CompanyRepository companyRepository;
    private final WorkRepository workRepository;
    private final StatusRepository statusRepository;
    private final GradeRepository gradeRepository;
    private final ProveImageRepository proveImageRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Employee employee = new Employee();
            Company companyOptional = companyRepository.findByCode((int) row.getCell(0).getNumericCellValue()).get();
            employee.setCompanay(companyOptional);

            Team teamOptional = teamRepository.findByCode((int) row.getCell(1).getNumericCellValue()).get();
            employee.setTeam(teamOptional);

            employee.setParentsCode((int)row.getCell(2).getNumericCellValue());

            Work workOptional = workRepository.findByCode((int) row.getCell(3).getNumericCellValue()).get();
            employee.setWork(workOptional);

            employee.setPositionType((int)row.getCell(4).getNumericCellValue());
            employee.setName(row.getCell(5).getStringCellValue());

            Grade gradeOptional = gradeRepository.findByCode((int)row.getCell(6).getNumericCellValue()).get();
            employee.setGrade(gradeOptional);

            employee.setPhone(row.getCell(7).getStringCellValue());
            employee.setPhone2(row.getCell(8).getStringCellValue());
            employee.setEmail(row.getCell(9).getStringCellValue());
            employee.setLocation((int)row.getCell(10).getNumericCellValue());
            employee.setTask(row.getCell(11).getStringCellValue());
            employee.setJoinDate(row.getCell(12).getStringCellValue());
            employee.setLeftDate(row.getCell(13).getStringCellValue());
            employee.setBirthDate(row.getCell(14).getStringCellValue());

            Status statusOptional = statusRepository.findByCode((int) row.getCell(16).getNumericCellValue()).get();
            employee.setStatus(statusOptional);

            save(employee);
        }
    }

    @Transactional
    public Long save(Employee employee) {
        validateDuplication(employee);

        employeeRepository.save(employee);
        return employee.getId();
    }

    private void validateDuplication(Employee employee) {
        if (employeeRepository.findAllByName(employee.getName()).stream().count() > 0) {
            throw  new IllegalStateException("동명이인입니다.");
        }
    }

    @Transactional
    public void update(String name){
        List<Employee> allByName = employeeRepository.findAllByName(name);
        Employee employee = allByName.get(0);
        employee.setTask(null);
    }

    public List<EmployeeDto> findAll() {
        List<Employee> list = employeeRepository.findAll();
        List<EmployeeDto> datas = new ArrayList<>();

        for (Employee employee:list) {
            EmployeeDto data = new EmployeeDto();
            data.setId(employee.getId());
            data.setGradeName(employee.getGrade().getKorName());
            data.setName(employee.getName());
            data.setEmail(employee.getEmail());
            data.setPhone(employee.getPhone());
            data.setStatus(employee.getStatus().getCode());
            data.setStatusKorName(employee.getStatus().getKorName());

            Optional<ProveImage> img = proveImageRepository.findByEmployeeId(employee.getId());
            if ( img.stream().count() > 0) {
                byte[] byteEnc64 = Base64.encodeBase64(img.get().getImg());
                String imgStr = null;
                try {
                    imgStr = new String(byteEnc64 , "UTF-8");
                    data.setImg(imgStr);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            datas.add(data);
        }
        return datas;
    }

    public List<Employee> findOne(String name) {
        return employeeRepository.findAllByName(name);
    }
}
