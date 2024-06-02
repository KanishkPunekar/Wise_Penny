package com.product.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Invoice {
	@Id
	private int invoiceId;
	private String invoiceDate;
	private int dueAmount;
	private int totalAmount;
	@ManyToOne
	private Customer customer;
}
