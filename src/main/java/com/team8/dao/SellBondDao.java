package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.team8.models.Order;
import com.team8.responses.OrderResponse;
import com.team8.utils.DataBaseConnectionUtil;

public class SellBondDao {
	
	private static final String USER_TRADER_CHECK_SQL = "SELECT * FROM customeraccountinfo WHERE CustomerId = ? AND TraderId = ?";
	private static final String CUST_BOND_QTY_CHECK = "SELECT * FROM portfolio WHERE CustomerId = ? AND CUSIP = ? AND Quantity >= ?";
	private static final String CURR_BOND_QTY_SQL = "SELECT Price, Quantity FROM bondinfo WHERE CUSIP = ?";
	private static final String UPDATE_BOND_QTY_SQL = "UPDATE bondinfo SET Quantity = ? WHERE CUSIP = ?";
	private static final String UPDATE_CUST_LIMIT_SQL = "UPDATE customeraccountinfo SET CreditLimit = ? WHERE CustomerId = ?";
	
	private int errorCode;
	private String responseMessage;
	private Connection databaseConnection = null;
	private PortfolioUpdateDao dao = null;

	private double previousCustomerLimit;
	private int previousBondQuantity;
	private double currentBondPrice;

	public SellBondDao() {
		errorCode = 200;
		responseMessage = "OK";
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
		previousCustomerLimit = 0;
		previousBondQuantity = -1;
		currentBondPrice = 0.0;
		dao = new PortfolioUpdateDao();
	}

	public OrderResponse sellBond(Map<String, String []> params, int customerId, int traderId) {
		OrderResponse sor = new OrderResponse();
		Order so = new Order();
		sor.setOrder(so);
		try {
			//check if trader is allowed to trade
			if(isTraderAuthorizedForUser(customerId, traderId)) {
				//Extract params from map				
				String cusip = params.get("cusip")[0];
				int quantity = Integer.parseInt(params.get("quantity")[0]);
				int sellQuantity = -1;
				double totalAmount = 0.0;
				
				//check if customer has enough bonds of type to sell
				if(doesCustomerHaveEnoughBonds(cusip, quantity, customerId)) {
					
					//update bond quantity
					if(updateBondQuantity(cusip, sellQuantity)) {

						//update customer balance
						totalAmount = quantity * currentBondPrice;
						if(updateCustomerBalance(customerId, totalAmount)) {
							//Set the parameters for the buy order here
							so.setCusip(cusip);
							so.setCustomerId(customerId);
							so.setPrice(totalAmount);
							so.setQuantity(quantity);
							so.setStatus("Order Placed");
							so.setOrderType("SELL");	
							dao.updateOnSell(so);
						}
					}										
				}				
			}
		}catch(Exception ex) {
			if(ex instanceof NullPointerException) {
			}
		}

		sor.setErrorCode(errorCode);
		sor.setResponseMessage(responseMessage);
		return sor;
	}
	
	private boolean updateCustomerBalance(int customerId, double totalAmount) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(UPDATE_CUST_LIMIT_SQL);
			st.setDouble(1, previousCustomerLimit + totalAmount);
			st.setInt(2, customerId);
			st.executeUpdate();
		}catch(Exception e) {
			return false;
		}		
		return true;
	}

	private boolean updateBondQuantity(String cusip, int sellQuantity) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(CURR_BOND_QTY_SQL);
			st.setString(1, cusip);
			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false) {
				errorCode = 111;
				responseMessage = "Invalid cusip";
				return false;
			}
			currentBondPrice = rs.getDouble(1);
			previousBondQuantity = rs.getInt(2);
			
		}catch(Exception e) {
			return false;
		}
		
		try {
			PreparedStatement st = databaseConnection.prepareStatement(UPDATE_BOND_QTY_SQL);
			st.setInt(1, previousBondQuantity + sellQuantity) ;
			st.setString(2, cusip);
			st.executeUpdate();
			
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	private boolean doesCustomerHaveEnoughBonds(String cusip, int quantity, int customerId) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(CUST_BOND_QTY_CHECK);
			st.setInt(1, customerId);
			st.setString(2, cusip);
			st.setInt(3, quantity);
			
			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false) {
				errorCode = 111;
				responseMessage = "Customer does not have bonds of this type";
				return false;
			}
			
			previousBondQuantity = rs.getInt(3);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
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

}
