package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.service.ServiceEmployee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private Repository repository;

    @InjectMocks
    private ServiceEmployee service;

    @Test
    public void whenSaveEmployee_shouldReturnEmployee() {
        Employee employee = new Employee();
        employee.setName("Mark");

        //тут создание ,гле any-что укогдно ,argument matchers-передает
        when(repository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getName()).isSameAs(employee.getName());
        verify(repository).save(employee);
    }

    @Test
    public void whenGivenId_shouldReturnEmployee_ifFound() {
        //тут тест ради теста
        Employee employee = new Employee();
        employee.setId(88);

        when(repository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Employee expected = service.getById(employee.getId());

        assertThat(expected).isSameAs(employee);
        verify(repository).findById(employee.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void should_throw_exception_when_employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setId(89);
        employee.setName("Mark");

        given(repository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(employee.getId());
    }

    @Test
    public void whenGivenName_shouldReturnListSalary() {
        Employee employee = new Employee();
        employee.setSalary(2000);

        //тут создание ,где any-что укогдно ,argument matchers-передает

        when(repository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        Employee created = service.create(employee);

        assertThat(created.getSalary()).isSameAs(employee.getSalary());
        verify(repository).save(employee);

    }

    @Test
    public void whenGivenName_shouldReturnListEmployee_with_phone() {
        Employee employee = new Employee();
        employee.setPhone("55-55");

        //тут создание ,где any-что укогдно ,argument matchers-передает
        when(repository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getPhone()).isSameAs(employee.getPhone());
        verify(repository).save(employee);
    }

    @Test
    public void whenGivenName_shouldReturnListCountry() {
        Employee employee = new Employee();
        employee.setCountry("Greece");

        //тут создание ,где any-что угодно ,argument matchers-передает
        when(repository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getCountry()).isSameAs(employee.getCountry());
        verify(repository).save(employee);
    }
    @Test
    public void whenGivenName_shouldReturnListByWorkDays() {
        Employee employee = new Employee();
        employee.setWorkdays(19);

        when(repository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getWorkdays()).isSameAs(employee.getWorkdays());
        verify(repository).save(employee);
    }
}