package com.team8.models;

public class Customer {
	private String customerName;
	private int customerId;
	
	public Customer(String userName, int userId) {
		this.customerId = userId;
		this.customerName = userName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}