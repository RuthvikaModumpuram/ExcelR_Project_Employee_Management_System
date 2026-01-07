package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private UserRepository userRepository;

    // ✅ EMPLOYEE can view ONLY their data
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Employee getMyProfile(Authentication authentication) {

        String email = authentication.getName(); // from JWT
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getEmployee();
    }
}
