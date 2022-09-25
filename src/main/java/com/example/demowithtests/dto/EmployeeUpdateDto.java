package com.example.demowithtests.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class EmployeeUpdateDto {
//при переезде изменение адреса и мейла
    public Integer id;

    @NotNull(message = "Phone may not be null")
    @Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters long")
    @Schema(description = "Name of an employee.", example = "44-44-55", required = true)
    public String phone;

    @Email
    @NotNull
    @Schema(description = "Email address of an employee.", example = "billys@mail.com", required = true)
    public String email;

    //todo: dfhgjkdfhg Jira - 5544
    public Date date = Date.from(Instant.now());
}