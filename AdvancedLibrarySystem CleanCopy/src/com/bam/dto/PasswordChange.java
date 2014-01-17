package com.bam.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PasswordChange {
	@Id
	private String token;
	private String email;
	private Date tokenExpirationTime;
	private int IdNumber;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getTokenExpirationTime() {
		return tokenExpirationTime;
	}
	public void setTokenExpirationTime(Date tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
	public int getIdNumber() {
		return IdNumber;
	}
	public void setIdNumber(int idNumber) {
		IdNumber = idNumber;
	}

}
