package com.sc.spring.finalex;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customers")
public class Customer {
	@Id
	private String id;
	private String address;
	private String reservation;
	private String details;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String address, String reservation, String details) {
		super();

		this.address = address;
		this.reservation = reservation;
		this.details = details;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}



}
