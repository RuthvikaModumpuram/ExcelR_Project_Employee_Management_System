package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeProfessional;

public interface EmployeeProfessionalRepository
        extends JpaRepository<EmployeeProfessional, Long> {

    Optional<EmployeeProfessional> findByEmployee(Employee employee);
}
