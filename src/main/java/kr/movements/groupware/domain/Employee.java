package kr.movements.groupware.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder   // 빌더 패턴 사용
@AllArgsConstructor // 모든 필드를 가지는 생성자 적용
@NoArgsConstructor  // 필드가 없는 생성자 적용
public class Employee extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_code") // fk로 저장될 컬럼명, default = pk를 대상으로 fk를 적용함.
    private Company companay;

    // N:1 fk
    @ManyToOne
    @JoinColumn(name = "team_code") // fk로 저장될 컬럼명, default = pk를 대상으로 fk를 적용함.
    private Team team;

    private int parentsCode;

    @ManyToOne
    @JoinColumn(name = "work_type") // fk로 저장될 컬럼명, default = pk를 대상으로 fk를 적용함.
    private Work work;
    private int positionType;
    private String name;

    @ManyToOne
    @JoinColumn(name = "grade_code") // fk로 저장될 컬럼명, default = pk를 대상으로 fk를 적용함.
    private Grade grade;
    private String phone;
    private String phone2;
    private String email;
    private int location;
    private String task;
    private String joinDate;
    private String leftDate;
    private String birthDate;

    @ManyToOne
    private Status status;

    private int prove; // 증명사진 fk
}
