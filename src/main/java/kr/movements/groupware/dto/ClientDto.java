package kr.movements.groupware.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientDto {
    private Long id;
    private int company_code;
    private String company_name;
    private String team_name;
    private String name;
    private String grade_name;
    private String tel;
    private String mobile;
    private String fax;
    private String email;
    private int zip;
    private String address;
    private String task;
    private String memo;
    private String front;
    private String rear;
    private Long front_id;
    private Long rear_id;
}
