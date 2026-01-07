package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_project")
public class EmployeeProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectCode;
    private String startDate;
    private String endDate;
    private String client;
    private String reportingManager;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false, unique = true)
    private Employee employee;

    // 🔹 mandatory no-arg constructor (IMPORTANT)
    public EmployeeProject() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProjectCode() { return projectCode; }
    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getReportingManager() { return reportingManager; }
    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

	@Override
	public String toString() {
		return "EmployeeProject [id=" + id + ", projectCode=" + projectCode + ", startDate=" + startDate + ", endDate="
				+ endDate + ", client=" + client + ", reportingManager=" + reportingManager + ", employee=" + employee
				+ "]";
	}

	public EmployeeProject(String projectCode, String startDate, String endDate, String client, String reportingManager,
			Employee employee) {
		super();
		this.projectCode = projectCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.client = client;
		this.reportingManager = reportingManager;
		this.employee = employee;
	}
	
    
}
