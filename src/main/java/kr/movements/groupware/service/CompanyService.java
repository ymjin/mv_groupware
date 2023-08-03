package kr.movements.groupware.service;

import kr.movements.groupware.domain.BusinessCardImage;
import kr.movements.groupware.dto.ClientDto;
import kr.movements.groupware.domain.Client;
import kr.movements.groupware.domain.Company;
import kr.movements.groupware.repository.BusinessCardImageRepository;
import kr.movements.groupware.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final BusinessCardImageRepository businessCardImageRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Company company = new Company();
            company.setCode((int)row.getCell(0).getNumericCellValue());
            company.setKorName(row.getCell(1).getStringCellValue());
            company.setEngName(row.getCell(2).getStringCellValue());

            if (row.getPhysicalNumberOfCells() == 3) {
                company.setCompanyType("");
                company.setInformation("");
            }
            else {
                company.setCompanyType(String.valueOf((int)row.getCell(3).getNumericCellValue()));
                if (row.getPhysicalNumberOfCells() == 4) {
                    company.setInformation("");
                }
                else {
                    company.setInformation(row.getCell(4).getStringCellValue());
                }
            }

            save(company);
        }
    }

    @Transactional
    public int save(Company company) {
        validateDuplication(company);
        companyRepository.save(company);
        return company.getCode();
    }

    private void validateDuplication(Company company) {
        //중복 검사
        // 1. 이름
        // 2. 전화번호
        // 3. 회사
        // 4. 직급
        // 5. 부서
//        companyRepository.findByName(company.getName()).ifPresent(m -> {
//            throw new IllegalStateException("동명이인입니다.");
//        });
    }

    public List<ClientDto> setCompanyName(List<Client> clients) {
        List<ClientDto> list = new ArrayList<>();

        for (Client client:clients) {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setCompany_code(client.getCompanay().getCode());
            clientDto.setCompany_name(client.getCompanay().getKorName());
            clientDto.setName(client.getName());
            clientDto.setMobile(client.getMobile());
            clientDto.setEmail(client.getEmail());
            clientDto.setTeam_name(client.getTeamName());
            clientDto.setGrade_name(client.getGradeName());
            clientDto.setFront_id(client.getFront());
            clientDto.setRear_id(client.getRear());
//            Optional<BusinessCardImage> front = businessCardImageRepository.findById(Long.valueOf(61));
//
//            if (front.stream().count() > 0) {
//                byte[] byteEnc64 = Base64.encodeBase64(front.get().getImg());
//                String imgStr = null;
//                try {
//                    imgStr = new String(byteEnc64 , "UTF-8");
//                    clientDto.setFront(imgStr);// .setImg(imgStr);
//                } catch (UnsupportedEncodingException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            Optional<BusinessCardImage> rear = businessCardImageRepository.findById(Long.valueOf(62));
//
//            if (rear.stream().count() > 0) {
//                byte[] byteEnc64 = Base64.encodeBase64(rear.get().getImg());
//                String imgStr = null;
//                try {
//                    imgStr = new String(byteEnc64 , "UTF-8");
//                    clientDto.setRear(imgStr);// .setImg(imgStr);
//                } catch (UnsupportedEncodingException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            list.add(clientDto);
        }

        return list;
    }
}
