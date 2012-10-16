package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.team8.models.BuyOrder;
import com.team8.responses.BuyOrderResponse;
import com.team8.utils.DataBaseConnectionUtil;

public class BuyBondDao {

	private static final String USER_TRADER_CHECK_SQL = "SELECT * FROM customeraccountinfo WHERE CustomerId = ? AND TraderId = ?";
	private static final String BOND_QTY_CHECK_SQL = "SELECT * FROM bondinfo WHERE CUSIP = ? AND Quantity >= ?";
	private static final String BOND_QUANTITY_UPDATE_SQL ="UPDATE bondinfo SET Quantity = ? WHERE CUSIP = ?";
	private static final String CUST_CREDIT_UPDATE_SQL = "UPDATE customeraccountinfo SET Limit = ? WHERE CustomerId = ?"; 
	private static final String UPDATE_PORTFOLIO_SQL = "";  

	private int errorCode;
	private String responseMessage;
	private Connection databaseConnection = null;

	private double previousCustomerLimit;
	private int previousBondQuantity;

	public BuyBondDao() {
		errorCode = 200;
		responseMessage = "OK";
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
		previousCustomerLimit = 0;
		previousBondQuantity = -1;
	}

	public BuyOrderResponse buyBond(Map<String, String []> params, int customerId, int traderId) {
		BuyOrderResponse bor = new BuyOrderResponse();
		BuyOrder bo = new BuyOrder();
		bor.setOrder(bo);

		try {
			//check if trader is allowed to trade
			if(isTraderAuthorizedForUser(customerId, traderId)) {
				//check if customer has enough credit
				String totalAmount = params.get("amount")[0];
				String cusip = params.get("cusip")[0];
				int quantity = Integer.parseInt(params.get("quantity")[0]);
				
				if(doesCustomerHaveCredit(totalAmount)) {
													
					if(checkBondQuantity(cusip, quantity)) {
						
						if(updateBondQuantity(cusip, quantity)) {	
							
							if(updateCustomerCredit(customerId, Double.parseDouble(totalAmount)));
						}
					}
				}
			}
		}catch(Exception ex) {

		}


		//place the order {check qty and update}
		//Update portfolio

		bor.setErrorCode(errorCode);
		bor.setResponseMessage(responseMessage);
		return bor;
	}

	private boolean doesCustomerHaveCredit(String totalAmount) {
		if(previousCustomerLimit < Double.parseDouble(totalAmount)) {
			return false;
		}
		return true;
	}
	private boolean checkBondQuantity(String cusip, int quantity) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(BOND_QTY_CHECK_SQL);
			st.setString(1, cusip);			
			st.setInt(2, quantity);

			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false) {
				errorCode = 111;
				responseMessage = "Not enough quantity available in market for selected bond";
				return false;
			}
			else {
				previousBondQuantity = rs.getInt(6);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean isTraderAuthorizedForUser(int customerId, int traderId) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(USER_TRADER_CHECK_SQL);
			st.setInt(1, customerId);
			st.setInt(2, traderId);

			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false) {
				errorCode = 111;
				responseMessage = "Trader not authorized for user";
				return false;
			}
			else {
				previousCustomerLimit = rs.getInt(3);				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean updateBondQuantity(String cusip, int orderQty) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(BOND_QUANTITY_UPDATE_SQL);
			st.setInt(1, previousBondQuantity - orderQty);
			st.setString(2, cusip);			
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean updateCustomerCredit(int customerId, double price) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(CUST_CREDIT_UPDATE_SQL);
			st.setDouble(1, previousCustomerLimit - price);
			st.setInt(2, customerId);			
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean updateCustomerPortfolio() {
		return true;
	}
}
