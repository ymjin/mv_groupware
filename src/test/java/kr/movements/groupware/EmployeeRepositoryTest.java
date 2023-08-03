package kr.movements.groupware;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(false)
public class EmployeeRepositoryTest {

//    @Autowired
//    EmployeeRepository employeeRepository;
//
//    @Test
//    @Transactional
//    public void testEmployee() throws Exception {
//        Employee employee = new Employee();
//        employee.setName("진영민");
//        employee.setPhone("01051336705");
//
//        Employee findEmployee = employeeRepository.save(employee);
//
//        Assertions.assertThat(findEmployee.getId()).isEqualTo(employee.getId());
////        Assertions.assertThat(findEmployee.getName()).isEqualTo(employee.getName());
//    }
}
