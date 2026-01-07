package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeProject;
import com.example.demo.repository.EmployeeProjectRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeProjectService {

    @Autowired
    private EmployeeProjectRepository projectRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    // ADMIN: add or update project details
    public EmployeeProject saveOrUpdate(EmployeeProject project) {

        Long empId = project.getEmployee().getEmpId();

        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Optional<EmployeeProject> existing =
                projectRepo.findByEmployee(employee);

        if (existing.isPresent()) {
            EmployeeProject old = existing.get();
            old.setProjectCode(project.getProjectCode());
            old.setStartDate(project.getStartDate());
            old.setEndDate(project.getEndDate());
            old.setClient(project.getClient());
            old.setReportingManager(project.getReportingManager());
            return projectRepo.save(old);
        }

        project.setEmployee(employee);
        return projectRepo.save(project);
    }
    
    public Employee getEmployeeById(Long empId) {
        return employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }


    // EMPLOYEE: view own project
    public Optional<EmployeeProject> getByEmployee(Employee employee) {
        return projectRepo.findByEmployee(employee);
    }
    public void deleteById(Long id) {
    	projectRepo.deleteById(id);
    }
}
