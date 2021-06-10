package com.spring.model;

import java.util.Date;

public class MessageAlertModel {
	private String alert, message;
	private Date date;
	
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MessageAlertModel(String alert, String message, Date date) {
		super();
		this.alert = alert;
		this.message = message;
		this.date = date;
	}
	
}
