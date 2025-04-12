package com.sc.spring.finalex;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reservations")
public class Reservation {
    @Id
    private String id;
    private String detailsReservation;
    private String ticketnumber;
    private String date;

    @DBRef
    private Payment payment;


	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String id, String details, String ticketnumber, String date, Payment payment) {
		super();
		this.id = id;
		this.detailsReservation = details;
		this.ticketnumber = ticketnumber;
		this.date = date;
		this.payment = payment;
	}
	
	// Getters and Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetailsReservation() {
		return detailsReservation;
	}

	public void setDetailsReservation(String details) {
		this.detailsReservation = details;
	}

	public String getTicketnumber() {
		return ticketnumber;
	}

	public void setTicketnumber(String ticketnumber) {
		this.ticketnumber = ticketnumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

    
	
    public void update(String newDetails) {
        this.detailsReservation = newDetails;
    }

    
    
}

