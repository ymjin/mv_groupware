package kr.movements.groupware.repository;

import kr.movements.groupware.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByName(String name);
    Optional<Employee> findByName(String name);
}
