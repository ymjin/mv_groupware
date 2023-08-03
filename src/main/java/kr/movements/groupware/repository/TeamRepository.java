package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {
    List<Team> findAllByKorName(String korName);

    Optional<Team> findByCode(int code);

    List<Team> findByLevel(int level);
}
