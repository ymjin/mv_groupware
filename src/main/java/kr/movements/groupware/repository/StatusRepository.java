package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status , Long> {
    List<Status> findAllByKorName(String korName);

    Optional<Status> findByKorName(String korName);
    Optional<Status> findByCode(int code);
}
