package com.bam.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class BookPieces {
	@Id @GeneratedValue
	private int bookPieceId;
	private boolean available;
	@ManyToOne
	private Books book;
	
	@OneToMany(mappedBy="bookPiece",cascade=CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	private Collection<Bookings> bookPieces= new ArrayList<Bookings>();
	
	public Collection<Bookings> getBookPieces() {
		return bookPieces;
	}
	public void setBookPieces(Collection<Bookings> bookPieces) {
		this.bookPieces = bookPieces;
	}
	
	public int getBookPieceId() {
		return bookPieceId;
	}
	public void setBookPieceId(int bookPieceId) {
		this.bookPieceId = bookPieceId;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Books getBook() {
		return book;
	}
	public void setBook(Books book) {
		this.book = book;
	}
	
}
