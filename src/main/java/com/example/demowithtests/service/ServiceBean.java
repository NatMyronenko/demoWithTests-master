package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
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
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service //кандидат в бины
public class ServiceBean implements Service {

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

    @Override
    public List<Employee> getEmployeeByPhone(String phone) {
        List<Employee> employees = repository.getEmployeeByPhone(phone);
        employees.stream().forEach(e -> System.err.println(e.getName() + "ДОброго вечора,ми з Украины"));
        return employees;
    }

    @Override
    public Page<Employee> getAllWithPagination(Pageable pageable) {
        log.debug("getAllWithPagination() - start: pageable = {}", pageable);
        Page<Employee> list = repository.findAll(pageable);
        log.debug("getAllWithPagination() - end: list = {}", list);
        return list;
    }

    @Override
    public Page<Employee> findByCountryContaining(String country, int page, int size, List<String> sortList, String sortOrder) {
        // create Pageable object using the page, size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return repository.findByCountryContaining(country, pageable);
    }

    @Override
    public Page<Employee> findBySalary(Integer salary, int page, int size, List<String> sortList, String sortOrder){

    Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
    return repository.findBySalary(salary,pageable);
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



}