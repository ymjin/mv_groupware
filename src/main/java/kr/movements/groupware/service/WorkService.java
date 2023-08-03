package kr.movements.groupware.service;

import kr.movements.groupware.domain.Work;
import kr.movements.groupware.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Work work = new Work();
            work.setCode((int) row.getCell(0).getNumericCellValue());
            work.setKorName(row.getCell(1).getStringCellValue());

            save(work);
        }
    }

    @Transactional
    public int save(Work work) {
        workRepository.save(work);
        return work.getCode();
    }
}