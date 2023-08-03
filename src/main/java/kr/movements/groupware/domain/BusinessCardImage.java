package kr.movements.groupware.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter @Setter
public class BusinessCardImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private byte[] img;
    private Long fileSize;
    private String originalName;
    private String fileName;
    private Long clientId;
    private int type; // 0 front 1 rear
}
