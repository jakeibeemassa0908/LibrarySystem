package com.bam.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
public class Books {
	@Id @GeneratedValue
	private int bookId;
	private String title;
	private String author;
	private int year;
	private int stock_number;
	private int availableOne;
	private String book_cover_link;
	private String ISBN;
	
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBook_cover_link() {
		return book_cover_link;
	}
	public void setBook_cover_link(String book_cover_link) {
		this.book_cover_link = book_cover_link;
	}
	public int getAvailableOne() {
		return availableOne;
	}
	public void setAvailableOne(int availableOne) {
		this.availableOne = availableOne;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	private String category;
	@Column(length=1000)
	private String description;
	private String publisher;
	@Temporal(TemporalType.DATE)
	private Date date_added;
	
	@OneToMany(mappedBy="book",cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<BookPieces> bookPieces= new ArrayList<BookPieces>();
	
	@OneToMany(mappedBy="book",cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<Comments> comments= new ArrayList<Comments>();
	
	@OneToMany(mappedBy="book",cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<Bookings> booking= new ArrayList<Bookings>();
	
	public Collection<Bookings> getBooking() {
		return booking;
	}
	public void setBooking(Collection<Bookings> booking) {
		this.booking = booking;
	}
	public Collection<BookPieces> getBookPieces() {
		return bookPieces;
	}
	public void setBookPieces(Collection<BookPieces> bookPieces) {
		this.bookPieces = bookPieces;
	}
	public Date getDate_added() {
		return date_added;
	}
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStock_number() {
		return stock_number;
	}
	public void setStock_number(int stock_number) {
		this.stock_number = stock_number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Comments> getComments() {
		return comments;
	}
	public void setComments(Collection<Comments> comments) {
		this.comments = comments;
	}
	
}
