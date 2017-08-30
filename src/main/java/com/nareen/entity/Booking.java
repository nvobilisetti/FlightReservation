package com.nareen.entity;

public class Booking {
	
	private int id;
	private String firstName;
	private String lastName;
	private String mailId;
	private long contact;
	private String flightNumber;
	private String confirmation;
	public Booking() {
	}
	public Booking(String firstName, String lastName, String mailId, long contact,
			String flightNumber, String confirmation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailId = mailId;
		this.contact = contact;
		this.flightNumber = flightNumber;
		this.confirmation = confirmation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	
	
	
	
}
