package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")  // 🔐 ALL APIs ADMIN ONLY
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    //  ADD employee (ADMIN)
    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    //  VIEW all employees (ADMIN)
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //  UPDATE employee (ADMIN)
    @PutMapping("/update-employee/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    //  DELETE employee (ADMIN)
    @DeleteMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
