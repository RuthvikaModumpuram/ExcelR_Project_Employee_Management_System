package com.example.demo.controller;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody RegisterRequestDTO request) {
        return authService.register(request, "ADMIN");
    }

    @PostMapping("/register/employee")
    public String registerEmployee(@RequestBody RegisterRequestDTO request) {
        return authService.register(request, "EMPLOYEE");
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

}
