package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class EmployeeReadDto {
//response in postman
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

//    @NotNull(message = "Country may not be null")
//    @Size(min = 2, max = 32, message = "Country must be between 2 and 32 characters long")
//    @Schema(description = "Country of an employee.", example = "Greece", required = true)
//    public String country;

    @Email
    @NotNull
    public String email;

    public Instant time =Instant.now();

//    @NotNull
//    public String phone;

    //todo: dfhgjkdfhg Jira - 5544
    //jira -менеджер задач
    //ставит текущую дату вызова
    public Date date = Date.from(Instant.now());

    public EmployeeReadDto(String name, String email) {
    }
}