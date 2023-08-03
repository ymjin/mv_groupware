package kr.movements.groupware.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Getter @Setter
public class ProveImage extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    private byte[] img;
//    private MediumBLOB img;
    private Long fileSize;
    private String originalName;
    private String fileName;
    private Long employeeId;
}
