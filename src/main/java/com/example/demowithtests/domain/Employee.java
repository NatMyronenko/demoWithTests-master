package com.example.demowithtests.domain;

import lombok.*;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
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
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private CardsSalary card;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_id")
    private  TaxCode taxCode;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee")
    private Set<Addresses> addresses = new HashSet<>();
}
