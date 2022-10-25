package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity//помечает те классы кот связаны с базой данных
@Table(name = "addres")
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "addresses_active")
    private Boolean addresses_active = Boolean.TRUE;
    @Column(name = "country")
    private String country;
    @Column(name="city")
    private String city;
    @Column(name = "street")
    private String street;


}
