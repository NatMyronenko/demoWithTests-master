package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class EmployeeRead2Dto {
    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @NotNull(message = "Salary may not be null.")
    @Size(min = 1, max = 10000, message = "Name must be between 1 and 1000000 characters long")
    @Schema(description = "Salary of employee .", example = "2100", required = true)
    public Integer salary;

    @NotNull
    public Integer workdays;

    //todo: dfhgjkdfhg Jira - 5544
    //jira -менеджер задач
    //ставит текущую дату вызова
    public Date date = Date.from(Instant.now());
}