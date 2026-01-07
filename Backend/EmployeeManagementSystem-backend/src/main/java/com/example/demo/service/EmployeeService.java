package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


    @Autowired
    private EmployeeRepository employeeRepository;

 // ✅ ADMIN adds employee → auto create USER
    @Transactional
    public Employee addEmployee(Employee employee) {

        // 1️⃣ Save employee ONCE
        Employee savedEmployee = employeeRepository.save(employee);

        // 2️⃣ Create employee login
        User user = new User();
        user.setEmail(employee.getEmail());
        user.setPassword(passwordEncoder.encode("emp123"));
        user.setRole("EMPLOYEE");
        user.setEmployee(savedEmployee);

        userRepository.save(user);

        return savedEmployee;
    }

    //  Get all employees (ADMIN)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //  Update employee (ADMIN)
    public Employee updateEmployee(Long empId, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());

        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(Long empId) {

        // 1️⃣ Delete USER first (if exists)
        userRepository.findByEmployee_EmpId(empId)
                      .ifPresent(userRepository::delete);

        // 2️⃣ Delete EMPLOYEE
        employeeRepository.deleteById(empId);
    }

}
