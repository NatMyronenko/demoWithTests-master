package com.example.demowithtests.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@Data
public class CardsSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;

}
