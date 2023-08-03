package kr.movements.groupware.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeamDto {
    private int code;
    private String korName;
    private String parentsCode;
    private int level;
    private Boolean hasChild;
//    private Boolean use;
}
