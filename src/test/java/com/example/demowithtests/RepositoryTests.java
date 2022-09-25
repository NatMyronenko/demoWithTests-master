package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTests {

    @Autowired
    private Repository repository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest() {
        //можно тут создать через new employee
        //в  данном случе идет создание через chain

        Employee employee = Employee.builder().name("Mark").country("England").build();


        repository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
        Assertions.assertThat(employee.getName()).isEqualTo("Mark");
        Assertions.assertThat(employee.getCountry()).isEqualTo("England");
    }

    @Test
    @Order(2)
    public void getEmployeeTest() {
        Employee employee = repository.findById(1).orElseThrow();
        repository.findById(1).orElseThrow();

        Assertions.assertThat(employee.getId()).isEqualTo(1);

    }

    @Test
    @Order(3)
    public void getListOfEmployeeTest() {


        List<Employee> employeesList = repository.findAll();

        Assertions.assertThat(employeesList.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {

        Employee employee = repository.findById(1).get();

        employee.setName("Martin");
        Employee employeeUpdated = repository.save(employee);

        Assertions.assertThat(employeeUpdated.getName()).isEqualTo("Martin");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        Employee employee = repository.findById(1).get();

        repository.delete(employee);

        //repository.deleteById(1L);

        Employee employee1 = null;

        Optional<Employee> optionalAuthor = Optional.ofNullable(repository.findByName("Martin"));

        if (optionalAuthor.isPresent()) {
            employee1 = optionalAuthor.get();
        }

        Assertions.assertThat(employee1).isNull();
    }

    @Test
    @Order(6)
    public void getEmployeeByNameTest() {
        Employee employee = new Employee();
        employee.setName("Mark");
        repository.save(employee);

        List<Employee> employeesListByName = repository.findAll();

        Assertions.assertThat(employeesListByName.get(0).getName()).isEqualTo("Mark");
    }
    @Test
    @Order(7)
    public  void getEmployeeBySalaryLessThan(){
        Employee employee = new Employee();
        employee.setSalary(1000);
        repository.save(employee);

        List<Employee> employeesListBySalaryLess = repository.findAll();
        Assertions.assertThat(employeesListBySalaryLess.get(0).getSalary()).isLessThan(2000);

    }

    @Test
    @Order(8)
    public  void findByPhoneIsNull() {
        Employee employee = new Employee();
        employee.setPhone("");
        //employee.setPhone(null); так не работает
        repository.save(employee);
        List<Employee> employeesListByPhone = repository.findAll();
        Assertions.assertThat(employeesListByPhone.get(0).getPhone().isEmpty());
    }
    @Test
    @Order(9)
    public void findUserByCountry(){
        Employee employee = new Employee();
        employee.setCountry("Greece");

        repository.save(employee);

        List<Employee> employeesListByCountry = repository.findAll();
        Assertions.assertThat(employeesListByCountry.get(0).getCountry()).isEqualTo("Greece");

    }
    @Test
    @Order(10)
    public void getEmployeeBySalaryGreaterThan(){
        Employee employee = Employee.builder().salary(3000).build();
        repository.save(employee);

        List<Employee> employeesListBySalaryGreater = repository.findAll();
        Assertions.assertThat(employeesListBySalaryGreater.get(0).getSalary()).isGreaterThan(2000);
    }

}
