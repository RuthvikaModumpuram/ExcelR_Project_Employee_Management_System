package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    private String email;
    private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisterRequestDTO [email=" + email + ", password=" + password + "]";
	}
	public RegisterRequestDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public RegisterRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}
