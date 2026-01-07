package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String role;
	public AuthResponseDTO(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public String getRole() {
		return role;
	}
    
}
