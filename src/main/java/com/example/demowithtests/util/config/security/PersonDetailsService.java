package com.example.demowithtests.util.config.security;

import com.example.demowithtests.domain.Person_password;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
@org.springframework.stereotype.Service
public class PersonDetailsService implements UserDetailsService {
    //будет предназначаться для spring security
    private final Service service;
    @Autowired
    public PersonDetailsService(Service service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person_password> person = service.findByUsername(username);
        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new PersonDetails(person.get());
    }

}
