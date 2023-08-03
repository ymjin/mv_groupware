package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository  extends JpaRepository<Location, Long> {
    List<Location> findAllByKorName(String KorName);
    Optional<Location> findByCode(int code);
}
