package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.service.ServiceBean;
import com.example.demowithtests.util.config.mapStrukt.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller implements ResponseController {


    private final Service service;
    private final ServiceBean serviceBean;//внедрили бин
    private final EmployeeMapper employeeMapper;


    //Операция сохранения юзера в базу данных
    // save dto
    @Override
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.create(employee);
    }


    //save 2 dto
    @Override
    @PostMapping("/users/save")
    @ResponseStatus(HttpStatus.CREATED)


    public EmployeeSaveDto saveEmployee2(@RequestBody Employee employee) {
        return employeeMapper.employeeSave2Dto(service.create(employee));
    }

    @Override
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    //read dto
    @Override
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getById(id);
    }


    //Получения юзера по id 2
    //read 2 dto
    @Override
    @GetMapping("/users/dto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto getEmployeeById2Dto(@PathVariable Integer id) {
        return employeeMapper.employeeReadDto(service.getById(id));
    }

    //Обновление юзера
    //update dto
    @Override
    @PatchMapping("/users/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdateDto updateDto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeMapper.employeeUpdateDto(service.updateById(id, employee));
    }

    //Обновление юзера 2
    //update 2 dto
    @Override
    @PatchMapping("/users/update2/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdateDto update2Dto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeMapper.employeeUpdateDto(service.updateById(id, employee));
    }

    @Override
    @GetMapping(value = "/users", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> get(@RequestParam(value = "name") String name) {
        return service.get(name);
    }

    @Override
    @GetMapping(value = "/users", params = {"salary"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getSalary(@RequestParam(value = "salary") Integer salary) {
        return service.getSalary(salary);
    }

    @Override
    @GetMapping(value = "/users", params = {"phone"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeWithPhone
            (@RequestParam(value = "phone") String phone) {
        return service.getEmployeeWithPhone(phone);
    }

    @Override
    @GetMapping(value = "/users", params = {"country"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getByCountry(@RequestParam(value = "country") String country) {
        return service.getByCountry(country);

    }

    @Override
    @GetMapping(value = "/users", params = {"workdays"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getByWorkDays(@RequestParam(value = "workdays") Integer workdays) {
        return service.getByWorkDays(workdays);

    }

    //Получение списка юзеров по коду телефона
    @Override
    @GetMapping("/users/phone/{phone}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByPhone(@PathVariable("phone") String phone) {
        return serviceBean.getEmployeeByPhone(phone);
    }

    //Получение списка юзеров по коду телефона 2 вариант
//    @GetMapping(value = "/users/phone", params = {"phone"})
//    @ResponseStatus(HttpStatus.OK)
//    public List<Employee> getEmployeeByPhone(@RequestParam String phone) {
//        return serviceBean.getEmployeeByPhone(phone);
//    }
//Удаление всех юзеров
    @Override
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    @GetMapping("/users/page")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> getPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return service.getAllWithPagination(paging);
    }

    @GetMapping("/users/country")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findByCountry(@RequestParam(required = false) String country,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "") List<String> sortList,
                                        @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        //Pageable paging = PageRequest.of(page, size);
        //Pageable paging = PageRequest.of(page, size, Sort.by("name").ascending());
        return service.findByCountryContaining(country, page, size, sortList, sortOrder.toString());
    }

    @GetMapping("/users/salary")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findBySalary(@RequestParam(required = false) Integer salary,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "2") int size,
                                      @RequestParam(defaultValue = "") List<String> sortList,
                                       @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {
       return service.findBySalary(salary, page, size, sortList, sortOrder.toString());

    }

    @GetMapping("/users/name")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findByName(@RequestParam(required = false) String name,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "4") int size,
                                      @RequestParam(defaultValue = "") List<String> sortList,
                                     @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder) {
        return service.findByName(name, page, size, sortList, sortOrder.toString());
    }
}