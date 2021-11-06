package com.DTOs.BusinessDtos;

public class RegisterDTO {
	String usersname;
	String password;
	String reEnter;
	String errorMessage;
	
	public String getUsersname() {
		return usersname;
	}
	public void setUsersname(String usersname) {
		this.usersname = usersname;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getUsername() {
		return usersname;
	}
	public void setUsername(String usersname) {
		this.usersname = usersname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReEnter() {
		return reEnter;
	}
	public void setReEnter(String reEnter) {
		this.reEnter = reEnter;
	}
}
