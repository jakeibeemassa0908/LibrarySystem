package com.bam.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bookings {
	@Id @GeneratedValue
	private int bookingId;
	@Temporal(TemporalType.DATE)
	private Date bookingDate;
	@Temporal(TemporalType.DATE)
	private Date issuedDate;
	@Temporal(TemporalType.DATE)
	private Date bookingReturnDate;
	private boolean issued;
	private boolean returned;
	@ManyToOne
	private BookPieces bookPiece;
	@ManyToOne
	private Books book;
	@ManyToOne
	private Students student;
	
	public int getBookingId() {
		return bookingId;
	}
	
	public boolean isIssued() {
		return issued;
	}
	
	public void setIssued(boolean issued) {
		this.issued = issued;
	}
	
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public Date getBookingDate() {
		return bookingDate;
	}
	
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public Date getBookingReturnDate() {
		return bookingReturnDate;
	}
	public void setBookingReturnDate(Date bookingReturnDate) {
		this.bookingReturnDate = bookingReturnDate;
	}
	
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	public BookPieces getBookPiece() {
		return bookPiece;
	}
	
	public void setBookPiece(BookPieces bookPiece) {
		this.bookPiece = bookPiece;
	}
	
	public Books getBook() {
		return book;
	}
	
	public void setBook(Books book) {
		this.book = book;
	}
	
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	
	public Date getIssuedDate() {
		return issuedDate;
	}
	
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	
	
}
