package com.example.demowithtests.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "taxcode")
@Data
public class TaxCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String code;
}
