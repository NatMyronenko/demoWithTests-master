package com.example.demowithtests.domain;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity//помечает те классы кот связаны с базой данных
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "passwordConfirm")
    private String passwordConfirm;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    private Set<Role> roles;

}