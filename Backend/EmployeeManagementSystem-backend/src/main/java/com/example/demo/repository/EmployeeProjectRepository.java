package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeProject;

public interface EmployeeProjectRepository
        extends JpaRepository<EmployeeProject, Long> {

    Optional<EmployeeProject> findByEmployee(Employee employee);
}
