package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@org.springframework.stereotype.Repository
//@Component

public interface Repository extends JpaRepository<Employee, Integer> {
    //here created all methods for sql and jpql requests
    Employee findByName(String name);

    @Query(value = "SELECT * FROM users WHERE name=? ", nativeQuery = true)
    List<Employee> getEmployeeByName(String name);

    // выдает список работников у которых зарплата меньше чем...(введенное значение)
    @Query(value = "SELECT * FROM users WHERE salary<?", nativeQuery = true)
    List<Employee> getEmployeeBySalaryLessThan(int salary);


    //выдает список работников у которых отсутсвует телефон
    @Query(value = "SELECT * FROM users where phone =?", nativeQuery = true)
    List<Employee> findByPhoneNotNull(String phone);

    //выдает список работников по запросу страны
    //JPQL запрос
    @Query("SELECT u FROM Employee u WHERE u.country = ?1")
    List<Employee> findUserByCountry(String country);

    //SQL заппрос к той же бизнес логике
//    @Query(value = "SELECT * FROM users where country=?", nativeQuery = true)
//    List<Employee> getEmployeeByCountry(String country);

    //выдает работника с макс количеством рабочих дней - не получилось((
//    @Query(value = "SELECT MAX(workdays) FROM users", nativeQuery = true)
//    Employee findEmployeeByWorkdays(Integer workdays);
    //выдает лист сотрудников у которых рабочих дней больше или равно чем ...?
    @Query(value = "SELECT * FROM users WHERE workdays>=?", nativeQuery = true)
    List<Employee> getEmployeeBySalaryGreaterThan(int salary);
    //JPQL запрос
//    @Query(value = "SELECT u from Employee u where u.workdays=?1",nativeQuery = true)
//    List<Employee> getEmployeeBySalaryGreaterThan(int salary);

    @Query(value = "SELECT * FROM Users u WHERE phone LIKE ?% ", nativeQuery = true)
    List<Employee> getEmployeeByPhone(String phone);


    Page<Employee> findByCountryContaining(String country, Pageable pageable);

    // поиск пользователей по имени с использованием пагинации, фильтрации сортировки
    Page<Employee> findByName(String name, Pageable pageable);

    Page<Employee> findBySalary(Integer salary, Pageable pageable);

    @Query("SELECT users.name,users.email from Employee users where users.email LIKE '%@gmail'")
    Page<Employee> findEmployeeByEmail(Pageable pageable);//1

    // поиск всех пользователей с использованием пагинации, фильтрации сортировки
    @NotNull
    Page<Employee> findAll(Pageable pageable);

    List<Employee> findAll();



}