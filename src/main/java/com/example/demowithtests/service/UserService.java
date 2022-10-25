package com.example.demowithtests.service;

import com.example.demowithtests.domain.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
