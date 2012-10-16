package com.team8.responses;

import java.util.List;
import com.team8.models.Holding;

public class PortfolioResponse {
	private List<Holding> holdings;
	private int errorCode;
	private String responseMessage;
	
	public List<Holding> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
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
