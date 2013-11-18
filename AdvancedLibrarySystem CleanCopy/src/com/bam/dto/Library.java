package com.bam.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Library {
	@Id @GeneratedValue
	private int libraryId;
	private String libraryName;
	private String libraryPassword;
	private String libraryUserName;
	private String libraryEmail;
	private long libraryPhoneNumber;
	
	public int getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public String getLibraryPassword() {
		return libraryPassword;
	}
	public void setLibraryPassword(String libraryPassword) {
		this.libraryPassword = libraryPassword;
	}
	public String getLibraryUserName() {
		return libraryUserName;
	}
	public void setLibraryUserName(String libraryUserName) {
		this.libraryUserName = libraryUserName;
	}
	public String getLibraryEmail() {
		return libraryEmail;
	}
	public void setLibraryEmail(String libraryEmail) {
		this.libraryEmail = libraryEmail;
	}
	public long getLibraryPhoneNumber() {
		return libraryPhoneNumber;
	}
	public void setLibraryPhoneNumber(long libraryPhoneNumber) {
		this.libraryPhoneNumber = libraryPhoneNumber;
	}
	

}
