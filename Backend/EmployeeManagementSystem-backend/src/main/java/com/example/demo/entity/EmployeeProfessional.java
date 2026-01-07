package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_professional")
public class EmployeeProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String officePhone;
    private String officeCity;
    private String reportingManager;
    private String hrName;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false, unique = true)
    private Employee employee;
    
    public EmployeeProfessional() {}


    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOfficePhone() { return officePhone; }
    public void setOfficePhone(String officePhone) { this.officePhone = officePhone; }

    public String getOfficeCity() { return officeCity; }
    public void setOfficeCity(String officeCity) { this.officeCity = officeCity; }

    public String getReportingManager() { return reportingManager; }
    public void setReportingManager(String reportingManager) { this.reportingManager = reportingManager; }

    public String getHrName() { return hrName; }
    public void setHrName(String hrName) { this.hrName = hrName; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
	@Override
	public String toString() {
		return "EmployeeProfessional [id=" + id + ", officePhone=" + officePhone + ", officeCity=" + officeCity
				+ ", reportingManager=" + reportingManager + ", hrName=" + hrName + ", employee=" + employee + "]";
	}
	public EmployeeProfessional(String officePhone, String officeCity, String reportingManager, String hrName,
			Employee employee) {
		super();
		this.officePhone = officePhone;
		this.officeCity = officeCity;
		this.reportingManager = reportingManager;
		this.hrName = hrName;
		this.employee = employee;
	}
	
    
    
}
