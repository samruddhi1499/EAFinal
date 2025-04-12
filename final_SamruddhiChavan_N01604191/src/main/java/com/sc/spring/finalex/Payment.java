package com.sc.spring.finalex;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("payments")
public class Payment {
	@Id
	private String id;
	private int amount;
	private String date;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment( int amount, String date) {
		super();
		this.amount = amount;
		this.date = date;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int calculate() {
		int tax = (int) (amount * 0.13);

		return amount + tax;
	}

}
