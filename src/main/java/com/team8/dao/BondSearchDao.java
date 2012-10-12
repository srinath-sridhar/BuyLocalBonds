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

public class BondSearchDao {
	
	private static final String BASE_SQL = "SELECT * FROM bondinfo WHERE";
	//the space before the actual clause is required!!!
	private static final String CUSIP_CLAUSE = " CUSIP = ?";
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
		for (int i=1; i<6; i++);
	}

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	private PreparedStatement createSqlStmt(Map<String, String[]> params) throws SQLException {
		StringBuffer sb = new StringBuffer();
		PreparedStatement st = null;
		//st.set
		//create the sql
		if(sb.toString() != null) {
			st = databaseConnection.prepareStatement(sb.toString());
			
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
