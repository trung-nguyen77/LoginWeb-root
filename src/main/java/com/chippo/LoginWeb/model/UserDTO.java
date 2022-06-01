package com.chippo.LoginWeb.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String email;
	private long phone;
	@NotBlank
	private String password;
	@NotBlank
	private String username;
	private String captcha;
	private String role;
	private boolean checkRemember = false;
	

	public Long getId() {
		return id;
	}

	public void setId(Long long1) {
		this.id = long1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isCheckRemember() {
		return checkRemember;
	}

	public void setCheckRemember(boolean checkRemember) {
		this.checkRemember = checkRemember;
	}

}
