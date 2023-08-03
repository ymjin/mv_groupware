package kr.movements.groupware.service;

import kr.movements.groupware.domain.Team;
import kr.movements.groupware.dto.TeamDto;
import kr.movements.groupware.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    int curLevel = 1;

    public void parsing(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            Team team = new Team();
            team.setCode((int) row.getCell(0).getNumericCellValue());
            team.setKorName(row.getCell(1).getStringCellValue());

            if ((int)row.getCell(2).getNumericCellValue() == 0) {
                team.setParentsCode("");
            }
            else {
                team.setParentsCode(String.valueOf((int)row.getCell(2).getNumericCellValue()));
            }

            team.setLevel((int) row.getCell(3).getNumericCellValue());
            team.setHasChild(row.getCell(4).getStringCellValue().equalsIgnoreCase("Y") ? true : false);
            team.setActive(row.getCell(5).getStringCellValue().equalsIgnoreCase("Y") ? true : false);

            save(team);
        }
    }

    @Transactional
    public int save(Team team) {
        //validateDuplication(team);

        teamRepository.save(team);
        return team.getCode();
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

    public List<TeamDto> findByLevel() {

        List<Team> result = teamRepository.findByLevel(curLevel++);
        List<TeamDto> dtoList = new ArrayList<>();
        for (Team team:result) {
            if (team.isActive()) {
                TeamDto dto = new TeamDto();

                dto.setCode(team.getCode());
                dto.setKorName(team.getKorName());
                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}
