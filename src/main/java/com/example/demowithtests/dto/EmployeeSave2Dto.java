package com.example.demowithtests.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeSave2Dto {
    public Integer id;

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @NotNull(message = "Salary may not be null.")
    @Size(min = 1, max = 10000, message = "Name must be between 1 and 1000000 characters long")
    @Schema(description = "Salary of employee .", example = "2100", required = true)
    public Integer salary;

    @NotNull(message = "Working days may not be null.")
    @Size(min = 1, max = 25, message = "Name must be between 1 and 25 characters long")
    @Schema(description = "Working days of employee .", example = "21", required = true)
    public Integer workdays;
}
