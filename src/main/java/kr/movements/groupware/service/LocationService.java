package kr.movements.groupware.service;

import kr.movements.groupware.domain.Grade;
import kr.movements.groupware.domain.Location;
import kr.movements.groupware.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Location location = new Location();
            location.setCode((int) row.getCell(0).getNumericCellValue());
            location.setKorName(row.getCell(1).getStringCellValue());
            location.setAddress(row.getCell(2).getStringCellValue());

            save(location);
        }
    }

    @Transactional
    public int save(Location location) {
        //validateDuplication(team);

        locationRepository.save(location);
        return location.getCode();
    }
}
