package com.bam.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
public class Comments {
	@Id @GeneratedValue
	private int commentID;
	private String commentTitle;
	@ManyToOne
	private Students student;
	@ManyToOne
	private Books book;
	private String commentUserName;
	private String commentContent;
	
	@Temporal(TemporalType.DATE)
	private Date commentDate;
	
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Books getBook() {
		return book;
	}
	public void setBook(Books book) {
		this.book = book;
	}
}
