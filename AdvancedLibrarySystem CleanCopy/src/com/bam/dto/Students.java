package com.bam.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Students {
	@Id @GeneratedValue
	private int StudentId;
	private String firstName;
	private String lName;
	private String department;
	private String program;
	private String password;
	private String email;
	private long phoneNumber;
	private String gender;
	private String library;
	@Temporal(TemporalType.DATE)
	private Date registerDate;
	@Lob
	@Column(name="PROFILE_PICTURE", nullable=false, columnDefinition="mediumblob")
	private byte[] profile_picture;
	
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<Comments> comments= new ArrayList<Comments>();
	
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<Bookings> booking= new ArrayList<Bookings>();

	public Collection<Bookings> getBooking() {
		return booking;
	}
	public void setBooking(Collection<Bookings> booking) {
		this.booking = booking;
	}
	public byte[] getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(byte[] profile_picture) {
		this.profile_picture = profile_picture;
	}
	public Collection<Comments> getComments() {
		return comments;
	}
	public void setComments(Collection<Comments> comments) {
		this.comments = comments;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getLibrary() {
		return library;
	}
	public void setLibrary(String library) {
		this.library = library;
	}
	
	

}
