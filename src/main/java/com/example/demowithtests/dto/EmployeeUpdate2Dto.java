package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class EmployeeUpdate2Dto {
    //при переезде изменение страны и адреса
   public Integer id;

    public String address;
    public String country;

    //todo: dfhgjkdfhg Jira - 5544
    public Date date = Date.from(Instant.now());
}