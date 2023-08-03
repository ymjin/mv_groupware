package kr.movements.groupware.service;

import kr.movements.groupware.domain.BusinessCardImage;
import kr.movements.groupware.domain.Client;
import kr.movements.groupware.domain.Company;
import kr.movements.groupware.repository.ClientRepository;
import kr.movements.groupware.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CompanyRepository companyRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Client clent = new Client();
            Company companyOptional = companyRepository.findByCode((int) row.getCell(0).getNumericCellValue()).get();
            clent.setCompanay(companyOptional);
            clent.setTeamName(row.getCell(1).getStringCellValue());
            clent.setName(row.getCell(2).getStringCellValue());
            clent.setGradeName(row.getCell(3).getStringCellValue());
            clent.setTel(row.getCell(4).getStringCellValue());
            clent.setMobile(row.getCell(5).getStringCellValue());
            clent.setFax(row.getCell(6).getStringCellValue());
            clent.setEmail(row.getCell(7).getStringCellValue());

            DataFormatter formatter = new DataFormatter();
            String s = formatter.formatCellValue(row.getCell(8));
            //clent.setZip(Integer.toString((int)row.getCell(8).getNumericCellValue()));
            clent.setZip(s);
            clent.setAddress(row.getCell(9).getStringCellValue());

            if (row.getCell(10) == null) {
                clent.setTask("");
            }
            else {
                clent.setTask(row.getCell(10).getStringCellValue());
            }

            if (row.getCell(11) == null) {
                clent.setMemo("");
            }
            else {
                clent.setMemo(row.getCell(11).getStringCellValue());
            }

            if (row.getCell(12) == null) {
                clent.setFront(null);
            }
            else {
                clent.setFront((long) row.getCell(12).getNumericCellValue());
            }

            if (row.getCell(13) == null) {
                clent.setRear(null);
            }
            else {
                clent.setRear((long) row.getCell(12).getNumericCellValue());
            }

            if (row.getCell(14) == null) {
                clent.setOriginalImageName("");
            }
            else {
                clent.setOriginalImageName(row.getCell(14).getStringCellValue());
            }

            save(clent);
        }
    }

    @Transactional
    public Long save(Client client) {
        validateDuplication(client);
        clientRepository.save(client);
        return client.getId();
    }

    private void validateDuplication(Client client) {
        //중복 검사
        // 1. 이름
        // 2. 전화번호
        // 3. 회사
        // 4. 직급
        // 5. 부서
        if (clientRepository.findAllByName(client.getName()).stream().count() > 0) {
            throw new IllegalStateException("동명이인입니다.");
        }
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public void updateImage(List<BusinessCardImage> images, Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        for (BusinessCardImage image:images) {
            if (image.getType() == 0) {
                // 0 front 1 rear
                clientOptional.get().setFront(image.getId());
            }
            else {
                clientOptional.get().setRear(image.getId());
            }
        }
    }

    public Optional<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }
}
