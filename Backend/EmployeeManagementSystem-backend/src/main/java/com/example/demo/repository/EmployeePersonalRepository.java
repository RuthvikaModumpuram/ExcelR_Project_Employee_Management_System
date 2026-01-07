package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeePersonal;

public interface EmployeePersonalRepository
        extends JpaRepository<EmployeePersonal, Long> {

    // 🔹 Find personal details by employee
    Optional<EmployeePersonal> findByEmployee(Employee employee);
}
