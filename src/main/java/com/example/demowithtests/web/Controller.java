package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.service.ServiceBean;
import com.example.demowithtests.util.config.EmployeeConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller {


    private final Service service;
    private final ServiceBean serviceBean;//внедрили бин
    private final EmployeeConverter converter;

    //Операция сохранения юзера в базу данных
    // save dto
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description =
            "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeSaveDto saveEmployee(@RequestBody @Valid EmployeeSaveDto requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.toSaveDto(service.create(employee));

        return dto;

    }

    //save 2 dto
    @PostMapping("/users/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description =
            "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeSave2Dto saveEmployee2(@RequestBody @Valid EmployeeSave2Dto requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.toSave2Dto(service.create(employee));

        return dto;
    }

    //Получение списка юзеров
    @Operation(summary = "This is endpoint to get  list employee .", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    //read dto
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.debug("getEmployeeById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toReadDto(employee);
        log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;

    }

    //Получения юзера по id 2
    //read 2 dto
    @GetMapping("/users/dto/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeRead2Dto getEmployeeById2(@PathVariable Integer id) {
        log.debug("getEmployeeById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toRead2Dto(employee);
        log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;

    }

    //Обновление юзера
    //update dto
    @PatchMapping("/users/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to update an employee by his id.", description =
            "Create request to update an employee bu id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.Employee by id was successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeUpdateDto updateDto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        EmployeeUpdateDto dto = converter.toUpdateDto(service.updateById(id, employee));
        return dto;
    }

    //Обновление юзера 2
    //update 2 dto
    @PatchMapping("/users/update2/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to update an employee by his id.", description =
            "Create request to update an employee bu id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.Employee by id was successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})

    public EmployeeUpdate2Dto update2Dto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        EmployeeUpdate2Dto dto = converter.toUpdate2Dto(service.updateById(id, employee));
        return dto;
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "This is endpoint delete all employees.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED. The employee list was successfully deleted."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    public void removeAllUsers() {
        service.removeAll();
    }


    @GetMapping(value = "/users", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to get  employee  by name.", description =
            "Create request to get employee by phone code.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee  was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    public List<Employee> get(@RequestParam(value = "name") String name) {
        return service.get(name);
    }

    @GetMapping(value = "/users", params = {"salary"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to get  employee list by working days more than value.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    public List<Employee> getSalary(@RequestParam(value = "salary") Integer salary) {
        return service.getSalary(salary);
    }

    @GetMapping(value = "/users", params = {"phone"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to get  employee list by phone not null.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})

    public List<Employee> getEmployeeWithPhone
            (@RequestParam(value = "phone") String phone) {
        return service.getEmployeeWithPhone(phone);
    }

    @GetMapping(value = "/users", params = {"country"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to get  employee list by country.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    public List<Employee> getByCountry(@RequestParam(value = "country") String country) {
        return service.getByCountry(country);

    }

    @GetMapping(value = "/users", params = {"workdays"})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to get  employee list by working days more than value.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    public List<Employee> getByWorkDays(@RequestParam(value = "workdays") Integer workdays) {
        return service.getByWorkDays(workdays);

    }

    //Получение списка юзеров по коду телефона
    @Operation(summary = "This is endpoint to get  employee by phone code.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})

    @GetMapping("/users/phone/{phone}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeeByPhone(@PathVariable("phone") String phone) {
        return serviceBean.getEmployeeByPhone(phone);
    }

////Получение списка юзеров по коду телефона 2 вариант
//    @GetMapping(value = "/users/phone", params = {"phone"})
//    @ResponseStatus(HttpStatus.OK)
//    public List<Employee> getEmployeeByPhone(@RequestParam String phone) {
//        return serviceBean.getEmployeeByPhone(phone);
//    }
}