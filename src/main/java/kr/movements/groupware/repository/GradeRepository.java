package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByKorName(String KorName);
    Optional<Grade> findByCode(int code);
}
