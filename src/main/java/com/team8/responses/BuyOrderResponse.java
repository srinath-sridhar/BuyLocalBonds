package com.team8.responses;

import com.team8.models.BuyOrder;

public class BuyOrderResponse {
	private BuyOrder order;
	private int errorCode;
	private String responseMessage;

	public BuyOrderResponse() {
		errorCode = 200;
		responseMessage = "OK";
		order = null;
	}

	public BuyOrder getOrder() {
		return order;
	}

	public void setOrder(BuyOrder order) {
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
