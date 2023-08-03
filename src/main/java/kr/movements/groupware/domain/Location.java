package kr.movements.groupware.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Getter @Setter
public class Location extends BaseEntity{
    @Id
    private int code;

    private String korName;
    private String address;
}
