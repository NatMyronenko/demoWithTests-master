package com.example.demowithtests.service;

import org.springframework.security.access.prepost.PreAuthorize;

public class AdminService {
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_SOME_OTHER')")
    public void doAdminStuff() {
        System.out.println("Only admin here");
    }
}
