package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Grade;
import kr.movements.groupware.domain.ProveImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveImageRepository  extends JpaRepository<ProveImage, Long> {
//    Optional<ProveImage> findByCode(int code);
    Optional<ProveImage> findByEmployeeId(Long id);
}
