package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeePersonal;
import com.example.demo.entity.EmployeeProfessional;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeProfessionalService;

@RestController
@RequestMapping("/professional")
@CrossOrigin("*")
public class EmployeeProfessionalController {

    @Autowired
    private EmployeeProfessionalService professionalService;

    @Autowired
    private UserRepository userRepository;

    // ADMIN: add/update professional details
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeProfessional saveProfessional(
            @RequestBody EmployeeProfessional professional) {

        return professionalService.saveOrUpdate(professional);
    }

// ================= ADMIN: View employee personal details =================
    
    @GetMapping("/admin/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeProfessional getProfessionalByEmpId(@PathVariable Long empId) {

        Employee employee = professionalService.getEmployeeById(empId);

        return professionalService.getByEmployee(employee)
                .orElse(null);
    }
    
    // EMPLOYEE: view own professional details
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public EmployeeProfessional getMyProfessional(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getEmployee() == null) {
            throw new RuntimeException("Employee not linked to user");
        }

        Employee employee = user.getEmployee();

        return professionalService.getByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("Professional details not found"));
    }

}
