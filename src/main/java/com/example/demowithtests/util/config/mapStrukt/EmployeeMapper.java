package com.example.demowithtests.util.config.mapStrukt;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeSaveDto employeeSave2Dto(Employee employee);

    Employee employeeSave2Dto(EmployeeSave2Dto employeeSave2DtoDto);

    EmployeeReadDto employeeReadDto(Employee employee);

    Employee employeeReadDto(EmployeeReadDto employeeReadDto);

    EmployeeUpdateDto employeeUpdateDto(Employee employee);

    Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);
    List<EmployeeReadDto> employeeGmail(Page<Employee> employee);
}