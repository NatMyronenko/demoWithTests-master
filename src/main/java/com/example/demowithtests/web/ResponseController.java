package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeSaveDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ResponseController {
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    Employee saveEmployee(@RequestBody Employee employee);


    @Operation(summary = "This is endpoint to add a new employee.", description =
            "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeSaveDto saveEmployee2(@RequestBody Employee employee);


    @Operation(summary = "This is endpoint to get  list employee .", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> getAllUsers();


    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    Employee getEmployeeById(@PathVariable Integer id);

    @Operation(summary = "This is endpoint returned a employee by his id.", description =
            "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeReadDto getEmployeeById2Dto(@PathVariable Integer id);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.Employee by id was successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeUpdateDto updateDto(@PathVariable("id") Integer id, @RequestBody Employee employee);

    @Operation(summary = "This is endpoint to update an employee by his id.", description =
            "Create request to update an employee bu id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.Employee by id was successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeUpdateDto update2Dto(@PathVariable("id") Integer id, @RequestBody Employee employee);
    @Operation(summary = "This is endpoint to get  employee  by name.", description =
            "Create request to get employee by phone code.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee  was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> get(@RequestParam(value = "name") String name);


    @Operation(summary = "This is endpoint to get  employee list by working days more than value.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> getSalary(@RequestParam(value = "salary") Integer salary);


    @Operation(summary = "This is endpoint to get  employee list by phone not null.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> getEmployeeWithPhone
            (@RequestParam(value = "phone") String phone);


    @Operation(summary = "This is endpoint to get  employee list by country.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> getByCountry(@RequestParam(value = "country") String country);


    @Operation(summary = "This is endpoint to get  employee list by working days more than value.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The employee list was successfully founded."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> getByWorkDays(@RequestParam(value = "workdays") Integer workdays);


    @Operation(summary = "This is endpoint to get  employee by phone code.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    List<Employee> getEmployeeByPhone(@PathVariable("phone") String phone);

    @Operation(summary = "This is endpoint delete all employees.", description =
            "Create request to get employee by phone code.", tags = {"Employee list"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DELETED. The employee list was successfully deleted."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "500", description = "Server ERROR!")})
    void removeAllUsers();

    @Operation(summary = "This is endpoint Find all the short names in the database" +
            "and return a list of short names", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    List<String> getAllLongNames();

    @Operation(summary = "This is endpoint Find all the short names in the database" +
            "and return a list of short names", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    Optional<Integer> findEmployeeByWorkDays();
    @Operation(summary = "This is endpoint Find all the short names in the database" +
            "and return a list of short names", description =
            "Create request to read all employee", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. Information was get successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    List<String> findAllDifCountries();

    //    public String registrationPage(@ModelAttribute("employee")Employee employee){
    //        return service.create();
    //    }


}

