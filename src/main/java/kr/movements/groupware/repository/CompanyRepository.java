package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByKorName(String korName);
    Optional<Company> findByKorName(String korName);
    Optional<Company> findByCode(int code);
}
