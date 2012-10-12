package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.team8.model.Bond;
import com.team8.utils.DataBaseConnectionUtil;


//Ignore this file for now
public class BondSearchDao {

	private static final String BASE_SQL = "SELECT * FROM bondinfo WHERE";
	//the space before the actual clause is required!!!
	private static final String RATING_CLAUSE = " Rating IN ";
	private static final String CUPON_CLAUSE = " Cupon BETWEEN ? and ?";
	private static final String PRICE_CLAUSE = " Price BETWEEN ? and ?";
	private static final String QTY_CLAUSE = " Quantity BETWEEN ? and ?";

	private Connection databaseConnection = null;
	private boolean isDefined [];
	private int count;


	public BondSearchDao() {
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
		isDefined = new boolean[6];
		isDefined[0] = true;
		for (int i=1; i<6; i++) {
			isDefined[i] = false;
		}
		count = 0;
	}

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	private PreparedStatement createSqlStmt(Map<String, String[]> params) throws SQLException {
		StringBuffer sb = new StringBuffer();
		String paramVals [] = null;
		PreparedStatement st = null;
		sb.append(BASE_SQL);

		paramVals = params.get("cusip");
		if(paramVals !=null && paramVals.length == 1) {

			if(count == 0) {
	//			sb.append(CUSIP_CLAUSE);
			}
			else {
		//		sb.append(" AND"+CUSIP_CLAUSE);
			}
			isDefined[1] = true;
			++count;
		}

		//		paramVals = params.get("rating_low");
		//		if(paramVals != null && paramVals.length == 1) {
		//			paramVals = params.get("rating_high");
		//			if(paramVals != null && paramVals.length == 1) {
		//				sb.append(b)
		//			}
		//		}

		paramVals = params.get("cupon_low");
		if(paramVals !=null && paramVals.length == 1) {
			paramVals = params.get("cupon_high");
			if(paramVals != null && paramVals.length == 1) {
				if(count == 0) {
					sb.append(CUPON_CLAUSE);
				}
				else {
					sb.append(" AND"+CUPON_CLAUSE);
				}
				isDefined[3] = true;
				++count;
			}			
		}

		paramVals = params.get("price_low");
		if(paramVals !=null && paramVals.length == 1) {
			paramVals = params.get("price_high");
			if(paramVals != null && paramVals.length == 1) {

				if(count == 0) {
					sb.append(PRICE_CLAUSE);
				}
				else {
					sb.append(" AND" +PRICE_CLAUSE);
				}
				isDefined[4] = true;
				++count;
			}			
		}

		paramVals = params.get("qty_low");
		if(paramVals !=null && paramVals.length == 1) {
			paramVals = params.get("qty_high");
			if(paramVals != null && paramVals.length == 1) {
				if(count == 0) {
					sb.append(QTY_CLAUSE);
				}
				else {
					sb.append(" AND"+QTY_CLAUSE);
				}
				isDefined[5] = true;
				++count;
			}			
		}

		//create the sql
		if(sb.toString() != null && !(sb.toString().equals(""))) {
			int paramCount = 1;
			st = databaseConnection.prepareStatement(sb.toString());
			if(isDefined[1]) {
				st.setString(paramCount, params.get("cusip")[0]);
				++paramCount;
			}
			if(isDefined[2]) {

			}
			if(isDefined[3]) {

			}
			if(isDefined[4]) {

			}
			if(isDefined[5]) {

			}

		}
		return st;
	}

	public List<Bond> searchBonds(Map<String, String[]> params) throws SQLException {
		List<Bond> results = new ArrayList<Bond>();
		PreparedStatement st = createSqlStmt(params);
		if(st != null) {
			ResultSet rs = st.executeQuery();
		}

		return results;
	}
}
