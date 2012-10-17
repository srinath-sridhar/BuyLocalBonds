package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team8.models.Holding;
import com.team8.models.Order;
import com.team8.responses.PortfolioResponse;
import com.team8.utils.DataBaseConnectionUtil;
import com.team8.utils.DateUtil;

public class PortfolioUpdateDao {

	private Connection databaseConnection = null;
	private static final String INSERT_SQL = "INSERT INTO portfolio  (CustomerId, CUSIP, Quantity, AveragePrice, LastUpdateDate) VALUES (?,?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE portfolio SET Quantity = ?, AveragePrice = ?, LastUpdateDate = ? WHERE CUSIP = ? AND CustomerId = ?";
	private static final String SELECT_SQL = "SELECT * FROM portfolio WHERE CUSIP = ? AND CustomerId = ?";
	private static final String RET_PORTFOLIO_SQL = "SELECT * FROM portfolio WHERE CustomerId = ? AND Quantity > 0";

	private int errorCode;
	private String responseMessage;

	public PortfolioUpdateDao() {
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
		errorCode = 200;
		responseMessage = "OK";
	}

	public boolean updateOnBuy(Order buyOrder) {
		try {

			PreparedStatement st = databaseConnection.prepareStatement(SELECT_SQL);
			st.setString(1, buyOrder.getCusip());
			st.setInt(2, buyOrder.getCustomerId());

			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false) {

				st = databaseConnection.prepareStatement(INSERT_SQL);
				st.setInt(1, buyOrder.getCustomerId());
				st.setString(2, buyOrder.getCusip());
				st.setInt(3, buyOrder.getQuantity());
				st.setDouble(4, buyOrder.getPrice());
				st.setDate(5, DateUtil.convertLongToDate(System.currentTimeMillis()));
				st.executeUpdate();
				st.close();

			}

			else {				
				int prevQty = rs.getInt(3);
				double avePrice = rs.getDouble(4);
				double currAvePrice = ((prevQty * avePrice) + (buyOrder.getQuantity() * buyOrder.getPrice())) / (prevQty + buyOrder.getQuantity());
				st = databaseConnection.prepareStatement(UPDATE_SQL);
				st.setInt(1, prevQty + buyOrder.getQuantity());
				st.setDouble(2, currAvePrice);
				st.setDate(3, DateUtil.convertLongToDate(System.currentTimeMillis()));
				st.setString(4, buyOrder.getCusip());
				st.setInt(5, buyOrder.getCustomerId());
				st.executeUpdate();
				st.close();
			}

		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateOnSell(Order sellOrder) {
		try {
			PreparedStatement st = databaseConnection.prepareStatement(SELECT_SQL);
			st.setString(1, sellOrder.getCusip());
			st.setInt(2, sellOrder.getCustomerId());

			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false) {
				st.close();
				return false;
			}
			else {
				int prevQty = rs.getInt(3);
				st.close();
				st = databaseConnection.prepareStatement(UPDATE_SQL);
				st.setInt(1, prevQty - sellOrder.getQuantity());
				st.setDouble(2, sellOrder.getPrice());
				st.setDate(3, DateUtil.convertLongToDate(System.currentTimeMillis()));
				st.setString(4, sellOrder.getCusip());
				st.setInt(5, sellOrder.getCustomerId());

				st.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}

	public PortfolioResponse getCustomerPortfolio(int customerId) {
		PortfolioResponse pfr = new PortfolioResponse();
		List<Holding> holdings = new ArrayList<Holding>();
		pfr.setHoldings(holdings);
		try {
			PreparedStatement st = databaseConnection.prepareStatement(RET_PORTFOLIO_SQL);
			st.setInt(1, customerId);
			ResultSet rs = st.executeQuery();
			if(rs == null || rs.next() == false ) {
				errorCode = 111;
				responseMessage = "This customer has no holdings";
			}
			else {
				do {
					holdings.add(convertToHolding(rs));
				}while(rs.next());
			}
		}catch(Exception ex) {
			errorCode = 111;
			responseMessage = "Database error";
		}
		
		pfr.setErrorCode(errorCode);
		pfr.setResponseMessage(responseMessage);
		return pfr;
	}

	private Holding convertToHolding(ResultSet rs) {
		Holding holding = new Holding();
		try {
			holding.setCusip(rs.getString(2));
			holding.setPurchaseQuantity(rs.getInt(3));
			holding.setPurchasePrice(rs.getDouble(4));		
			holding.setPurchaseDate(rs.getDate(5));
		}
		catch(Exception e) {
			return null;
		}
		return holding;
	}

}
