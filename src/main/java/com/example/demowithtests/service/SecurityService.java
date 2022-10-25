package com.example.demowithtests.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}


