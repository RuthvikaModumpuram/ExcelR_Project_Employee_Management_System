package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeFinance;
import com.example.demo.repository.EmployeeFinanceRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeFinanceService {

    @Autowired
    private EmployeeFinanceRepository financeRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    // ADMIN: save or update finance details
    public EmployeeFinance saveOrUpdate(EmployeeFinance finance) {

        Long empId = finance.getEmployee().getEmpId();

        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Optional<EmployeeFinance> existing =
                financeRepo.findByEmployee(employee);

        if (existing.isPresent()) {
            EmployeeFinance old = existing.get();
            old.setBankName(finance.getBankName());
            old.setBranch(finance.getBranch());
            old.setIfscCode(finance.getIfscCode());
            old.setAadhaarNumber(finance.getAadhaarNumber());
            old.setPanCard(finance.getPanCard());
            old.setCtcBreakup(finance.getCtcBreakup());
            return financeRepo.save(old);
        }

        finance.setEmployee(employee);
        return financeRepo.save(finance);
    }
    
    public Employee getEmployeeById(Long empId) {
        return employeeRepo.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }


    // EMPLOYEE: view own finance details
    public Optional<EmployeeFinance> getByEmployee(Employee employee) {
        return financeRepo.findByEmployee(employee);
    }
    public void deleteById(Long id) {
    	financeRepo.deleteById(id);
    }
}
