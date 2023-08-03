package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByName(String name);
    Optional<Client> findByName(String name);
}
