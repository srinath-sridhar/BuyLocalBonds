package com.team8.models;

public class User {
	private String userName;
	private String accountType;
	private int userId;
	
	public User(String userName, String accountType, int userId) {
		this.userId = userId;
		this.userName = userName;
		this.accountType = accountType;
	}

	public String getUserName() {
		return userName;
	}

	public String getAccountType() {
		return accountType;
	}

	public int getUserId() {
		return userId;
	}

}
