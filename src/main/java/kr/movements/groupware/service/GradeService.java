package kr.movements.groupware.service;

import kr.movements.groupware.domain.Grade;
import kr.movements.groupware.domain.Team;
import kr.movements.groupware.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Grade grade = new Grade();
            grade.setCode((int) row.getCell(0).getNumericCellValue());
            grade.setKorName(row.getCell(1).getStringCellValue());

            save(grade);
        }
    }

    @Transactional
    public int save(Grade grade) {
        //validateDuplication(team);

        gradeRepository.save(grade);
        return grade.getCode();
    }

    private void validateDuplication(Team team) {
//        team.findById
//        team.findByName(team.getName()).ifPresent(m -> {
//            throw  new IllegalStateException("동명이인입니다.");
//        });
    }

//    public List<Team> findEmployees() {
//        return teamRepository.findAll();
//    }
//
//    public Optional<Team> findOne(String name) {
//        return teamRepository.findByName(name);
//    }
}