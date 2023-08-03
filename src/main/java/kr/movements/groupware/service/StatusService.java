package kr.movements.groupware.service;

import kr.movements.groupware.domain.Status;
import kr.movements.groupware.domain.Work;
import kr.movements.groupware.repository.StatusRepository;
import kr.movements.groupware.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Status status = new Status();
            status.setCode((int) row.getCell(0).getNumericCellValue());
            status.setKorName(row.getCell(1).getStringCellValue());

            save(status);
        }
    }

    @Transactional
    public int save(Status status) {
        statusRepository.save(status);
        return status.getCode();
    }
}