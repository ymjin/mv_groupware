package kr.movements.groupware.repository;

import kr.movements.groupware.domain.BusinessCardImage;
import kr.movements.groupware.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessCardImageRepository  extends JpaRepository<BusinessCardImage, Long> {
    List<BusinessCardImage> findAllByClientId(Long clientId);
}
