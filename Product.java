package com.product.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private int srno;
	private String productName;
	private int price;
	private String dealer;
	private String description;
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int pid, int srno, String productName, int price,String dealer, String description, User user) {
		super();
		this.pid = pid;
		this.srno = srno;
		this.productName = productName;
		this.price = price;
		this.dealer = dealer;
		this.description = description;
		this.user = user;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	@Override
//	public String toString() {
//		return "Product [pid=" + pid + ", srno=" + srno + ", productName=" + productName + ", price=" + price
//				+ ", dealer=" + dealer + ", description=" + description + ", user=" + user + "]";
//	}
	
	
	
	
}
