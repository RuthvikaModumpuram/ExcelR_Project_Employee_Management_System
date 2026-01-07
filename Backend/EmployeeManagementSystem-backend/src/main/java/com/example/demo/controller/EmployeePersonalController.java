package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeePersonal;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeePersonalService;

@RestController
@RequestMapping("/personal")
@CrossOrigin("*")
public class EmployeePersonalController {

    @Autowired
    private EmployeePersonalService personalService;

    @Autowired
    private UserRepository userRepository;

    // ================= ADMIN APIs =================

    //  ADMIN: Add / Update employee personal details
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeePersonal savePersonalDetails(
            @RequestBody EmployeePersonal personal) {

        return personalService.saveOrUpdate(personal);
    }
 // ================= ADMIN: View employee personal details =================
    
    @GetMapping("/admin/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeePersonal getPersonalByEmpId(@PathVariable Long empId) {

        Employee employee = personalService.getEmployeeById(empId);

        return personalService.getByEmployee(employee)
                .orElse(null);
    }


    // ================= EMPLOYEE APIs =================

    //  EMPLOYEE: View own personal details
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public EmployeePersonal getMyPersonalDetails(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = user.getEmployee();

        return personalService.getByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("Personal details not found"));
    }
}
