package kr.movements.groupware.domain;

import kr.movements.groupware.converter.BooleanToYNConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Team extends BaseEntity {
    @Id
    private int code;

    private String korName;
    private String parentsCode;
    private int level;

//    @Convert(converter = BooleanToYNConverter.class)
    private boolean hasChild;
    private boolean isActive;
}
