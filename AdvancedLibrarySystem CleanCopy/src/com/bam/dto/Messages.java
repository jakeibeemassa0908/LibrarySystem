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
	
	public boolean isOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(boolean openFlag) {
		this.openFlag = openFlag;
	}
	public String getMessageFromString() {
		return messageFromString;
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
	
	@Column(length=1000)
	public String messageContent;
	
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
	
	@Temporal(TemporalType.DATE)
	public Date messageDate;
	
	

}
