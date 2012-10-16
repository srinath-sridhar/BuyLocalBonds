package com.team8.responses;

import com.team8.models.Order;

public class OrderResponse {
	private Order order;
	private int errorCode;
	private String responseMessage;

	public OrderResponse() {
		errorCode = 200;
		responseMessage = "OK";
		order = null;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
