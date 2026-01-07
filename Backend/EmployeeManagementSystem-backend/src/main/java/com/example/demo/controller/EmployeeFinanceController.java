package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeFinance;
import com.example.demo.entity.EmployeePersonal;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeFinanceService;

@RestController
@RequestMapping("/finance")
@CrossOrigin("*")
public class EmployeeFinanceController {

    @Autowired
    private EmployeeFinanceService financeService;

    @Autowired
    private UserRepository userRepository;

    // ADMIN: add/update finance
    @PostMapping("/admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeFinance saveFinance(
            @RequestBody EmployeeFinance finance) {

        return financeService.saveOrUpdate(finance);
    }
    
// ================= ADMIN: View employee personal details =================
    
    @GetMapping("/admin/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeFinance getFinanceByEmpId(@PathVariable Long empId) {

        Employee employee = financeService.getEmployeeById(empId);

        return financeService.getByEmployee(employee)
                .orElse(null);
    }

    // EMPLOYEE: view own finance
    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public EmployeeFinance getMyFinance(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = user.getEmployee();

        return financeService.getByEmployee(employee)
                .orElseThrow(() -> new RuntimeException("Finance details not found"));
    }
}
