package kr.movements.groupware.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Grade extends BaseEntity {
    @Id
    private int code;

    private String korName;
}
