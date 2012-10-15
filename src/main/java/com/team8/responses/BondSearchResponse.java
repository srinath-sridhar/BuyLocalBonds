package com.team8.responses;

import java.util.List;

import com.team8.models.Bond;

public class BondSearchResponse {
	private List<Bond> bonds;
	private int errorCode;
	private String responseMessage;
	
	public BondSearchResponse() {
		bonds = null;
		errorCode = 0;
		responseMessage = "";
	}
	
	public BondSearchResponse(List<Bond> bonds, int errorCode, String responseMessage) {
		this.bonds = bonds;
		this.errorCode = errorCode;
		this.responseMessage = responseMessage;
	}
	
	public List<Bond> getBonds() {
		return bonds;
	}
	
	public void setBonds(List<Bond> bonds) {
		this.bonds = bonds;
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
