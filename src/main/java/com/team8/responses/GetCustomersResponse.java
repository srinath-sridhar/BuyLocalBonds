package com.team8.responses;

import java.util.List;

import com.team8.models.Customer;

public class GetCustomersResponse {
	private Customer activeCustomer;
	private List<Customer> customers;
	private int errorCode;
	private String responseMessage;
	
	public GetCustomersResponse() {
		customers = null;
		errorCode = 200;
		responseMessage = "OK";
	}

	public Customer getActiveCustomer() {
		return activeCustomer;
	}

	public void setActiveCustomer(Customer activeCustomer) {
		this.activeCustomer = activeCustomer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
