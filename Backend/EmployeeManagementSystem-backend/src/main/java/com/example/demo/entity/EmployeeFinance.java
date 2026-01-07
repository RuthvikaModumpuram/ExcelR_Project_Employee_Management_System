package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_finance")
public class EmployeeFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;
    private String branch;
    private String ifscCode;
    private String aadhaarNumber;
    private String panCard;
    private String ctcBreakup;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false, unique = true)
    private Employee employee;

    // ✅ REQUIRED BY HIBERNATE
    public EmployeeFinance() {}

    public EmployeeFinance(String bankName, String branch, String ifscCode,
                           String aadhaarNumber, String panCard,
                           String ctcBreakup, Employee employee) {
        this.bankName = bankName;
        this.branch = branch;
        this.ifscCode = ifscCode;
        this.aadhaarNumber = aadhaarNumber;
        this.panCard = panCard;
        this.ctcBreakup = ctcBreakup;
        this.employee = employee;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getCtcBreakup() {
		return ctcBreakup;
	}

	public void setCtcBreakup(String ctcBreakup) {
		this.ctcBreakup = ctcBreakup;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeFinance [id=" + id + ", bankName=" + bankName + ", branch=" + branch + ", ifscCode=" + ifscCode
				+ ", aadhaarNumber=" + aadhaarNumber + ", panCard=" + panCard + ", ctcBreakup=" + ctcBreakup
				+ ", employee=" + employee + "]";
	}

    
    
}
