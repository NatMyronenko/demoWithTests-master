package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Person_password;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.exeption.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service //кандидат в бины
public class ServiceBean implements Service {
//here we injected beans
    private final Repository repository;


    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        //.orElseThrow(ResourceNotFoundException::new);
         /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setPhone(employee.getPhone());
                   entity.setAddress(employee.getAddress());
                    entity.setSalary(employee.getSalary());
                    entity.setWorkdays(employee.getWorkdays());

                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Employee employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //employee.setIsDeleted(true);
        repository.delete(employee);
        //repository.save(employee);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();

    }

    public List<Employee> get(String name) {
        return repository.getEmployeeByName(name);
    }

    public List<Employee> getSalary(int salary) {
        return repository.getEmployeeBySalaryLessThan(salary);
    }

    @Override

    public List<Employee> getEmployeeWithPhone(String phone) {
        return repository.findByPhoneNotNull(phone);
    }

    @Override
    public List<Employee> getByCountry(String country) {
        return repository.findUserByCountry(country);
    }

    public List<Employee> getByWorkDays(Integer workdays) {
        return repository.
                getEmployeeBySalaryGreaterThan(workdays);
    }
//-----------------method with log----------
    @Override
    public Page<Employee> getAllWithPagination(Pageable pageable) {
        log.debug("getAllWithPagination() - start: pageable = {}", pageable);
        Page<Employee> list = repository.findAll(pageable);
        log.debug("getAllWithPagination() - end: list = {}", list);
        return list;
    }
//----------methods with pagination,filters and sorts--------------
    @Override
    public Page<Employee> findByCountryContaining(String country, int page, int size, List<String> sortList, String sortOrder) {
        // create Pageable object using the page, size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return repository.findByCountryContaining(country, pageable);
    }

    @Override
    public Page<Employee> findBySalary(Integer salary, int page, int size, List<String> sortList, String sortOrder) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findBySalary(salary, pageable);
    }

    @Override
    public Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findByName(name, pageable);
    }


    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

    @Override//3
    public Page<Employee> findEmployeeByEmail(int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findEmployeeByEmail(pageable);
    }

    @Override
    public Page<Employee> findAll(int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findAll(pageable);
    }

    //-----------------STREAMS--------------
    @Override
    public List<EmployeeReadDto> getUsers() {
        List<Employee> users = repository.findAll();
        return users.stream()
                .map(employee -> new EmployeeReadDto(employee.getName(), employee.getEmail()))
                .collect(Collectors.toList());
    }


    @Override
    public List<Employee> getEmployeeByPhone(String phone) {
        List<Employee> employees = repository.getEmployeeByPhone(phone);
        employees.stream().forEach(e -> System.err.println(e.getName() + "ДОброго вечора,ми з Украины"));
        return employees;
    }
    //get all names with stream from database where name = 5 symbols
    @Override
    public List<String> findLongNames() {
        var shortNames = repository.findAll();
        return shortNames.stream()
                .map(n -> n.getName())
                .filter(n -> n.length() == 5)
                .collect(Collectors.toList());
    }

//find employee with max work days from database
    @Override
    public Optional<Integer> findMaxWorkDays() {
        var employeeList = repository.findAll();

        var workDays = employeeList.stream()
                .map(Employee::getWorkdays)
                .collect(Collectors.toList());
        var opt = workDays
                .stream()
                .min(Comparator.reverseOrder());

        return opt;
    }
//show list with non-duplicate countries
    @Override
    public List<String> findDifferentCountries() {
        var differentCountries = repository.findAll();
        return differentCountries.stream()
                .map(c->c.getCountry())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Person_password> findByUsername(String username) {
        return Optional.empty();
    }


}