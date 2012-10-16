package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.team8.models.Bond;
import com.team8.responses.BondSearchResponse;
import com.team8.utils.DataBaseConnectionUtil;
import com.team8.utils.DateUtil;
import com.team8.utils.Rating;


//Ignore this file for now
public class BondSearchDao {

	private int errorCode;
	private String responseMessage;

	private static final String BASE_SQL = "SELECT * FROM bondinfo WHERE";
	//the space before the actual clause is required!!!
	
	private static final String RATING_CLAUSE = " Rating IN ";
	private static final String COUPON_CLAUSE = " Coupon BETWEEN ? AND ?";
	private static final String CURRENT_YIELD_CLAUSE = " CurrentYield BETWEEN ? AND ?";
	private static final String YTM_CLAUSE = " YieldToMaturity BETWEEN ? AND ?";
	private static final String MATURITY_DATE_CLAUSE = " Maturity BETWEEN ? AND ?";
	private static final String PAR_VALUE_CLAUSE = "ParValue BETWEEN ? AND ?";
	private static final String PRICE_CLAUSE = " Price BETWEEN ? AND ?";
	private static final String CUSIP_CLAUSE = " CUSIP = ?";
	//private static final String QTY_CLAUSE = " Quantity BETWEEN ? and ?";

	private Connection databaseConnection = null;
	private boolean isDefined [];
	private int count;


	public BondSearchDao() {
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
		isDefined = new boolean[9];
		isDefined[0] = true;
		for (int i=1; i<9; i++) {
			isDefined[i] = false;
		}
		count = 0;
		errorCode = 200;
		responseMessage = "OK";
	}

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	private String generateRatingsClause(int num) {
		StringBuffer sb = new StringBuffer("");
		sb.append("(");
		for(int i=0; i<num; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}
	private PreparedStatement createSqlStmt(Map<String, String[]> params) {
		StringBuffer sb = new StringBuffer("");
		String paramVals [] = null;
		PreparedStatement st = null;
		sb.append(BASE_SQL);

		int numRatings = -1;
		paramVals = params.get("rating_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			String low, high;
			low = paramVals[0];
			paramVals = params.get("rating_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
				high = paramVals[0];
				numRatings = Rating.getNumRatings(low, high); 
				if(numRatings > 0) {
					sb.append(RATING_CLAUSE);
					sb.append(generateRatingsClause(numRatings));
					isDefined[1] = true;
					++count;
				}
			}
		}

		paramVals = params.get("coupon_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			paramVals = params.get("coupon_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
				if(count == 0) {
					sb.append(COUPON_CLAUSE);
				}
				else {
					sb.append(" AND"+COUPON_CLAUSE);
				}
				isDefined[2] = true;
				++count;
			}			
		}

		paramVals = params.get("currentYield_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			paramVals = params.get("currentYield_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
				if(count == 0) {
					sb.append(CURRENT_YIELD_CLAUSE);
				}
				else {
					sb.append(" AND"+CURRENT_YIELD_CLAUSE);
				}
				isDefined[3] = true;
				++count;
			}			
		}


		paramVals = params.get("yieldToMaturity_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			paramVals = params.get("yieldToMaturity_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
				if(count == 0) {
					sb.append(YTM_CLAUSE);
				}
				else {
					sb.append(" AND"+YTM_CLAUSE);
				}
				isDefined[4] = true;
				++count;
			}
		}

		paramVals = params.get("maturityDate_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			paramVals = params.get("maturityDate_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
				if(count == 0) {
					sb.append(MATURITY_DATE_CLAUSE);
				}
				else {
					sb.append(" AND"+MATURITY_DATE_CLAUSE);
				}
				isDefined[5] = true;
				++count;
			}
		}

		paramVals = params.get("parValue_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			paramVals = params.get("parValue_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {

				if(count == 0) {
					sb.append(PAR_VALUE_CLAUSE);
				}
				else {
					sb.append(" AND" +PAR_VALUE_CLAUSE);
				}
				isDefined[6] = true;
				++count;

			}			
		}
		paramVals = params.get("price_low");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			paramVals = params.get("price_high");
			if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {

				if(count == 0) {
					sb.append(PRICE_CLAUSE);
				}
				else {
					sb.append(" AND" +PRICE_CLAUSE);
				}
				isDefined[7] = true;
				++count;
			}			
		}
		
		paramVals = params.get("cusip");
		if(paramVals!=null && !("".equals(paramVals[0])) && paramVals.length == 1) {
			if(count == 0) {
				sb.append(CUSIP_CLAUSE);
			}
			else {
				sb.append(" AND" +CUSIP_CLAUSE);
			}
			isDefined[8] = true;
			++count;
		}



		//Done with SQL statement.. Now insert params
		if(count <= 0) {
			errorCode = 111;
			responseMessage = "no params defined";
			return null;
		}

		//add parameters to prepared statement
		if( sb.toString() != null && !("".equals( sb.toString()))) {
			int paramCount = 1; 

			try {
				st = databaseConnection.prepareStatement(sb.toString());
				if(isDefined[1]) {
					//Write stuff for the ratings system
					responseMessage = "Invalid input for ratings";					
					String low = params.get("rating_low")[0];
					String high = params.get("rating_high")[0];
					List<String> ratings = Rating.getRatingsBetween(low, high);
					if(ratings == null) {
						errorCode = 111;
						return null;
					}
					for(String rating : ratings) {
						st.setString(paramCount, rating);
						++paramCount;
					}
					responseMessage = "OK";
				}

				if(isDefined[2]) {
					responseMessage = "Invalid input for coupon. only decimal values permitted";
					st.setDouble(paramCount, Double.parseDouble(params.get("coupon_low")[0]));
					++paramCount;
					st.setDouble(paramCount, Double.parseDouble(params.get("coupon_high")[0]));
					++paramCount;
					responseMessage = "OK";
				}
				if(isDefined[3]) {
					responseMessage = "Invalid input for current yield. only decimal values permitted";
					st.setDouble(paramCount, Double.parseDouble(params.get("currentYield_low")[0]));
					++paramCount;
					st.setDouble(paramCount, Double.parseDouble(params.get("currentYield_high")[0]));
					++paramCount;
					responseMessage = "OK";

				}
				if(isDefined[4]) {
					responseMessage = "Invalid input for yield to maturity. only decimal values permitted";
					st.setDouble(paramCount, Double.parseDouble(params.get("yieldToMaturity_low")[0]));
					++paramCount;
					st.setDouble(paramCount, Double.parseDouble(params.get("yieldToMaturity_high")[0]));
					++paramCount;
					responseMessage = "OK";

				}
				if(isDefined[5]) {
					responseMessage = "Invalid input for maurity date. use format yyyy-mm-dd";
					st.setDate(paramCount, DateUtil.convertStringToDate(params.get("maturityDate_low")[0]));
					++paramCount;
					st.setDate(paramCount, DateUtil.convertStringToDate(params.get("maturityDate_high")[0]));
					++paramCount;
				}
				if(isDefined[6]) {
					responseMessage = "Invalid input for par value. only decimal values permitted";
					st.setDouble(paramCount, Double.parseDouble(params.get("parValue_low")[0]));
					++paramCount;
					st.setDouble(paramCount, Double.parseDouble(params.get("parValue_high")[0]));
					++paramCount;
					responseMessage = "OK";
				}
				if(isDefined[7]) {
					responseMessage = "Invalid input for price. only decimal values permitted";
					st.setDouble(paramCount, Double.parseDouble(params.get("price_low")[0]));
					++paramCount;
					st.setDouble(paramCount, Double.parseDouble(params.get("price_high")[0]));
					++paramCount;
					responseMessage = "OK";
				}
				if(isDefined[8]) {
					responseMessage = "Invalid input for CUSIP";
					st.setString(paramCount, params.get("cusip")[0]);
					responseMessage = "OK";
				}
			}
			catch(Exception ex) {				
				errorCode = 111;
				st = null;
			}
		}
		return st;
	}

	public BondSearchResponse searchBonds(Map<String, String[]> params) {
		//	List<Bond> results = new ArrayList<Bond>();
		BondSearchResponse bsr = new BondSearchResponse();
		PreparedStatement st = createSqlStmt(params);


		if(st == null) {
			bsr.setBonds(new ArrayList<Bond>());
		}

		else {
			List<Bond> bonds = executeQuery(st);
			bsr.setBonds(bonds);
		}

		bsr.setErrorCode(errorCode);
		bsr.setResponseMessage(responseMessage);
		return bsr;
	}

	private List<Bond> executeQuery(PreparedStatement st) {
		List<Bond> bonds = new ArrayList<Bond>();
		ResultSet rs;
		try {
			rs = st.executeQuery();
			while(rs.next()) {
				Bond temp = convertToBond(rs);
				if( temp != null) {
					bonds.add(temp);
				}
			}
		} catch (SQLException e) {
			errorCode = 111;
			responseMessage = "Error Accessing Database";
			return bonds;
		}
		if(bonds.size() <= 0) {
			errorCode = 111;
			responseMessage = "No bonds match the search criteria";
		}
		return bonds;
	}

	private Bond convertToBond(ResultSet rs) {
		Bond bond = new Bond();
		try {
			bond.setCusip(rs.getString(1));
			bond.setRating(rs.getString(2));
			bond.setCoupon(rs.getDouble(3));
			bond.setMaturityDate(rs.getDate(4));
			bond.setPrice(rs.getDouble(5));
			bond.setQuantityAvailable(rs.getInt(6));
			bond.setParValue(rs.getDouble(7));
			bond.setCurrentYield(rs.getDouble(8));
			bond.setYieldToMaturity(rs.getDouble(9));

		} catch (SQLException e) {
			bond = null;
			e.printStackTrace();
		}
		return bond;
	}
}
