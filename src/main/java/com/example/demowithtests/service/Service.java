package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface Service {

    Employee create(Employee employee);


    List<Employee> getAll();

    Employee getById(Integer id);

   // Employee updateById(Integer id);1

    Employee updateById(Integer id, Employee employee);

    void removeById(Integer id);

    void removeAll();

    List<Employee> get(String name);
    List<Employee> getSalary( int salary);
    List<Employee> getEmployeeWithPhone(String phone);
    List<Employee> getByCountry(String country);
    List<Employee> getByWorkDays(Integer workdays);

    List<Employee> getEmployeeByPhone(String phone);

}
