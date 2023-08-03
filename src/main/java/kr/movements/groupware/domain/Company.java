package kr.movements.groupware.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Company extends BaseEntity {
    @Id
    private int code;

    private String korName;
    private String engName;
    private String companyType;
    private String information;
}
