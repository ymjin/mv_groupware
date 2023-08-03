package kr.movements.groupware.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_code") // fk로 저장될 컬럼명, default = pk를 대상으로 fk를 적용함.
    private Company companay;

    private String teamName;
    private String name;
    private String gradeName;
    private String tel;
    private String mobile;
    private String fax;
    private String email;
    private String zip;
    private String address;
    private String task;
    private String memo;
    private Long front;
    private Long rear;
    private String originalImageName;
}
