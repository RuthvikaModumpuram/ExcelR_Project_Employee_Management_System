package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeePersonal;
import com.example.demo.entity.EmployeeProfessional;
import com.example.demo.repository.EmployeeProfessionalRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeProfessionalService {

    @Autowired
    private EmployeeProfessionalRepository professionalRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    // ADMIN: save or update professional details
    public EmployeeProfessional saveOrUpdate(EmployeeProfessional professional) {

        Long empId = professional.getEmployee().getEmpId();

        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // check existing record
        Optional<EmployeeProfessional> existing =
                professionalRepo.findByEmployee(employee);

        if (existing.isPresent()) {
            EmployeeProfessional old = existing.get();
            old.setOfficePhone(professional.getOfficePhone());
            old.setOfficeCity(professional.getOfficeCity());
            old.setReportingManager(professional.getReportingManager());
            old.setHrName(professional.getHrName());
            return professionalRepo.save(old);
        }

        professional.setEmployee(employee);
        return professionalRepo.save(professional);
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }


    public Optional<EmployeeProfessional> getByEmployee(Employee employee) {
        return professionalRepo.findByEmployee(employee);
    }

    public void deleteById(Long id) {
    	professionalRepo.deleteById(id);
    }
}
