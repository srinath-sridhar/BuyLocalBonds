package com.team8.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.PreparedStatement;
import com.team8.models.Customer;
import com.team8.responses.GetCustomersResponse;
import com.team8.utils.DataBaseConnectionUtil;

public class GetCustomersDao {
	private int errorCode;
	private String responseMessage;
	private Connection databaseConnection = null;

	private static final String BASE_SQL = "SELECT * FROM customeraccountinfo WHERE TraderId = ?";
	private static final String CUSTOMER_INFO_SQL = "SELECT * FROM personalinfo WHERE AccountId = ?";
	
	public GetCustomersDao() {
		errorCode = 200;
		responseMessage = "OK";
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
	}
	
	public GetCustomersResponse getCustomers(int customerId) {
		GetCustomersResponse gcr = new GetCustomersResponse();
		List<Customer> customers = new ArrayList<Customer>();
		PreparedStatement st = null;
		try {
			st = databaseConnection.prepareStatement(BASE_SQL);
			st.setInt(1, customerId);
		} 
		catch (SQLException e) {
			gcr.setCustomers(customers);
			gcr.setErrorCode(111);
			gcr.setResponseMessage("database error");
			e.printStackTrace();
		}
		
		if(st == null) {
			gcr.setCustomers(customers);
			
		}
		
		else {
			customers = executeQuery(st);
			gcr.setCustomers(customers);
			if(gcr.getCustomers().size() >= 1) {
				gcr.setActiveCustomer(customers.get(0));
			}
		}
		
		gcr.setErrorCode(errorCode);
		gcr.setResponseMessage(responseMessage);
		return gcr;
	}

	private List<Customer> executeQuery(PreparedStatement st) {
		List<Customer> customers = new ArrayList<Customer>();
		try {	
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				customers.add(convertToCustomer(rs));
			}
		} 
		catch (SQLException e) {		
			e.printStackTrace();
		}
		
		if(customers.size() <= 0) {
			errorCode = 111;
			responseMessage = "No customers found for this user";
		}
		return customers;
	}

	private Customer convertToCustomer(ResultSet rs) throws SQLException {
		String customerName = getCustomerName(rs.getInt(1));
		if(customerName == null) {
			return null;
		}
		return new Customer(customerName, rs.getInt(1));
	}

	private String getCustomerName(int customerId) {
		PreparedStatement st;
		try {
			st = databaseConnection.prepareStatement(CUSTOMER_INFO_SQL);
			st.setInt(1, customerId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getString(2) + " " + rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		return null;
	}

}
