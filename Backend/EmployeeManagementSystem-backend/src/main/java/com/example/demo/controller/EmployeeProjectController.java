package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeePersonal;
import com.example.demo.entity.EmployeeProject;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    // ================= ADMIN =================
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeProject saveProject(
            @RequestBody EmployeeProject project) {

        return projectService.saveOrUpdate(project);
    }
    
// ================= ADMIN: View employee personal details =================
    
    @GetMapping("/admin/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeProject getProjectByEmpId(@PathVariable Long empId) {

        Employee employee = projectService.getEmployeeById(empId);

        return projectService.getByEmployee(employee)
                .orElse(null);
    }

    // ================= EMPLOYEE =================
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public EmployeeProject getMyProject(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = user.getEmployee();

        return projectService.getByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("Project details not found"));
    }
}
