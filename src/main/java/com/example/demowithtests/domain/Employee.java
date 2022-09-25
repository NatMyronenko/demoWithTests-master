package com.example.demowithtests.domain;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String country;
    private String email;
    private String phone;
    private String address;
    private Integer salary;
    private Integer workdays;

}
