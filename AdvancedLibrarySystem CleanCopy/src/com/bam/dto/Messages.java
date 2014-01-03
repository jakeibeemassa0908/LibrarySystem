package com.bam.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Messages {
	@Id @GeneratedValue
	private int messageId;
	private int messageFrom;
	private int messageTo;
	private String messageFromString;
	private String messageToString;
	private String messageSubject;
	private boolean openFlag;
	@Column(length=1000)
	private String messageContent;
	@Temporal(TemporalType.DATE)
	private Date messageDate;
	private boolean deletedByStudent;
	private boolean deletedByAdmin;
	
	
	public boolean isDeletedByStudent() {
		return deletedByStudent;
	}

	public void setDeletedByStudent(boolean deletedByStudent) {
		this.deletedByStudent = deletedByStudent;
	}

	public boolean isDeletedByAdmin() {
		return deletedByAdmin;
	}

	public void setDeletedByAdmin(boolean deletedByAdmin) {
		this.deletedByAdmin = deletedByAdmin;
	}

	public boolean isOpenFlag() {
		return openFlag;
	}
	
	public void setOpenFlag(boolean openFlag) {
		this.openFlag = openFlag;
	}
	
	public void setMessageFromString(String messageFromString) {
		this.messageFromString = messageFromString;
	}
	
	public String getMessageToString() {
		return messageToString;
	}
	
	public void setMessageToString(String messageToString) {
		this.messageToString = messageToString;
	}
	
	public int getMessageId() {
		return messageId;
	}
	
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	public int getMessageFrom() {
		return messageFrom;
	}
	
	public void setMessageFrom(int messageFrom) {
		this.messageFrom = messageFrom;
	}
	
	public int getMessageTo() {
		return messageTo;
	}
	
	public void setMessageTo(int messageTo) {
		this.messageTo = messageTo;
	}
	
	public String getMessageSubject() {
		return messageSubject;
	}
	
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	public Date getMessageDate() {
		return messageDate;
	}
	
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	
	public String getMessageFromString() {
		return messageFromString;
	}
	
}
