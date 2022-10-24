package com.example.demowithtests.util.config.security;

import com.example.demowithtests.domain.Person_password;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PersonDetails implements UserDetails {
    private final Person_password person_password;

    public PersonDetails(Person_password person_password) {
        this.person_password = person_password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.person_password.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person_password.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //нужно для того чтобы получать данные аутентифицированного пользователя
    public Person_password getPerson(){
        return this.person_password;
    }
}
