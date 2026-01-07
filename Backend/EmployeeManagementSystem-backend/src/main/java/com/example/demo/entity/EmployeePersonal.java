package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_personal")
public class EmployeePersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateOfBirth;
    private Integer age;
    private String gender;
    private String address;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    // 🔹 Constructors
    public EmployeePersonal() {}

    public EmployeePersonal(String dateOfBirth, Integer age, String gender,
                            String address, Employee employee) {
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.employee = employee;
    }

    // 🔹 Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

	@Override
	public String toString() {
		return "EmployeePersonal [id=" + id + ", dateOfBirth=" + dateOfBirth + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + ", employee=" + employee + "]";
	}
    
}
