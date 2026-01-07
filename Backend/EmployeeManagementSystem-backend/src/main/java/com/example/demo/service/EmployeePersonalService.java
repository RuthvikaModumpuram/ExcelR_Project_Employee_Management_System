package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeePersonal;
import com.example.demo.repository.EmployeePersonalRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeePersonalService {

    @Autowired
    private EmployeePersonalRepository personalRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public EmployeePersonal saveOrUpdate(EmployeePersonal personal) {

        Long empId = personal.getEmployee().getEmpId();

        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Optional<EmployeePersonal> existing =
                personalRepo.findByEmployee(employee);

        if (existing.isPresent()) {
            EmployeePersonal old = existing.get();
            old.setDateOfBirth(personal.getDateOfBirth());
            old.setAge(personal.getAge());
            old.setGender(personal.getGender());
            old.setAddress(personal.getAddress());
            return personalRepo.save(old);
        }

        personal.setEmployee(employee);
        return personalRepo.save(personal);
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Optional<EmployeePersonal> getByEmployee(Employee employee) {
        return personalRepo.findByEmployee(employee);
    }

    public void deleteById(Long id) {
        personalRepo.deleteById(id);
    }
}


