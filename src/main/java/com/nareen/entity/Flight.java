package com.nareen.entity;

public class Flight {
	private int id;
	private String flightNumber;
	private String flightName;
	private String fromLoc;
	private String toLoc;
	private int availableTickets;
	private long ticketPrice;
	
	public Flight(String flightNumber, String flightName, String fromLoc, String toLoc, int availableTickets,
			long ticketPrice) {
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.fromLoc = fromLoc;
		this.toLoc = toLoc;
		this.availableTickets = availableTickets;
		this.ticketPrice = ticketPrice;
	}

	public Flight() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFromLoc() {
		return fromLoc;
	}

	public void setFromLoc(String fromLoc) {
		this.fromLoc = fromLoc;
	}

	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(String toLoc) {
		this.toLoc = toLoc;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	public long getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", flightName=" + flightName + ", fromLoc="
				+ fromLoc + ", toLoc=" + toLoc + ", availableTickets=" + availableTickets + ", ticketPrice="
				+ ticketPrice + "]";
	}
	
	
	
	
	
}
