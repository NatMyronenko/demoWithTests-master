package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Service {

    Employee create(Employee employee);


    List<Employee> getAll();

    Employee getById(Integer id);

    // Employee updateById(Integer id);1

    Employee updateById(Integer id, Employee employee);

    void removeById(Integer id);

    void removeAll();

    List<Employee> get(String name);

    List<Employee> getSalary(int salary);

    List<Employee> getEmployeeWithPhone(String phone);

    List<Employee> getByCountry(String country);

    List<Employee> getByWorkDays(Integer workdays);

    List<Employee> getEmployeeByPhone(String phone);

    Page<Employee> getAllWithPagination(Pageable pageable);

    //Page<Employee> findByCountryContaining(String country, Pageable pageable);

    /**
     * @param country   Filter for the country if required
     * @param page      number of the page returned
     * @param size      number of entries in each page
     * @param sortList  list of columns to sort on
     * @param sortOrder sort order. Can be ASC or DESC
     * @return Page object with customers after filtering and sorting
     */
    Page<Employee> findByCountryContaining(String country, int page, int size, List<String> sortList, String sortOrder);

   /**
    * "Find all employees with a given salary, and return them in a pageable format."
    *
    * The first parameter is the salary to search for. The second and third parameters are the page number and page size,
    * respectively. The fourth parameter is a list of sort fields, and the fifth parameter is the sort order
    *
    * @param salary The salary to search for
    * @param page The page number to be retrieved.
    * @param size The number of records per page.
    * @param sortList A list of fields to sort by.
    * @param sortOrder The sort order, either "asc" or "desc".
    * @return A Page<Employee> object.
    */
   Page<Employee> findBySalary(Integer salary, int page, int size, List<String> sortList, String sortOrder);

    /**
     * Find all employees with the given name, and return them in a page of size, sorted by the given sortList in the
     * given sortOrder.
     *
     * @param name      The name of the employee to search for.
     * @param page      The page number to retrieve.
     * @param size      The number of records per page.
     * @param sortList  A list of fields to sort by.
     * @param sortOrder The sort order, either "asc" or "desc".
     * @return Page<Employee>
     */
    Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder);
  //  Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder);

    /**
     * It returns a page of employees with a given email address.
     *
     * @param page The page number you want to retrieve.
     * @param size The number of records to be returned in a page.
     * @param sortList List of fields to sort by.
     * @param sortOrder The sort order. Can be either "asc" or "desc".
     * @return A Page<Employee> object is being returned.
     */
    Page<Employee> findEmployeeByEmail (int page, int size, List<String> sortList, String sortOrder);//2


    /**
     * Find all employees, sort them by the given list of fields, and return the page of results.
     *
     * @param page The page number to be retrieved.
     * @param size The number of records to return per page.
     * @param sortList A list of fields to sort by.
     * @param sortOrder The sort order, either "asc" or "desc".
     * @return A Page<Employee> object.
     */
    Page<Employee> findAll(int page, int size, List<String> sortList, String sortOrder);

    List<EmployeeReadDto> getUsers();
    /**
     * Find all the names that are longer than 5 characters.
     *
     * @return A list of strings.
     */
    List<String> findLongNames();

    Optional<Integer> findMaxWorkDays();
    List<String> findDifferentCountries();


    //----------security---------

}
