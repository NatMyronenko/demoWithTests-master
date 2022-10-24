package com.example.demowithtests.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Entity
@Table(name = "person_password")
public class Person_password {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name cant be empty")
    @Size(min = 2, max = 100, message = "name should have size from 2 until 100 characters")
    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    public Person_password() {
    }
}
