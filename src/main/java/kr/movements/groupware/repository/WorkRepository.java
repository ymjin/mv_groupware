package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllByKorName(String korName);

    Optional<Work> findByKorName(String korName);
    Optional<Work> findByCode(int code);
}
