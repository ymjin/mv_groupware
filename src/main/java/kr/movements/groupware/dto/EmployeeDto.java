package kr.movements.groupware.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDto {

    private String companay;
    private long id;
    private int teamCode;
    private int parentsCode;
    private int workType;
    private int positionType;
    private String name;
    private int gradeCode;
    private String gradeName;
    private String phone;
    private String phone2;
    private String email;
    private int location;
    private String task;
    private String join;
    private int status;
    private String statusKorName;
    private String img;
}
