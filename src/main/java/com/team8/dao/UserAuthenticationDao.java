package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


import com.team8.models.Customer;
import com.team8.utils.DataBaseConnectionUtil;

public class UserAuthenticationDao {

	private static final String BASE_SQL = "SELECT * FROM accounts WHERE";
	private static final String USERNAME_CLAUSE = " UserName = ?";
	private boolean isDefined [];
	private int count;

	private Connection databaseConnection = null;



	public UserAuthenticationDao() {
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
		isDefined = new boolean[2];
		isDefined[0] = true;
		isDefined[1] = false;
		count = 0;
	}

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}
	
	//For test purposes
	public void setDatabaseConnection(Connection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	private PreparedStatement createSqlStmt(Map<String, String[]> params) throws SQLException {

		StringBuffer sb = new StringBuffer("");
		sb.append(BASE_SQL);

		if(params.get("username")!=null && params.get("username").length == 1) {
			sb.append(USERNAME_CLAUSE);
			isDefined[1] = true;
			++count;
		}
		if(count <=0 ) {
			return null;
		}
		PreparedStatement st = databaseConnection.prepareStatement(sb.toString());
		if(isDefined[1]) {
			st.setString(1, params.get("username")[0]);
		}

		return st;
	}
	
	public Customer isUserAuthenticated(Map<String, String[]> params) throws SQLException {		
		
		String username = params.get("username")[0];
		String password = params.get("password")[0];
		
		if(("".equals(username))|| ("".equals(password))) {
			return null;
		}
		
		PreparedStatement st = createSqlStmt(params);
		if(st != null) {
			if(databaseConnection == null) {
				return null;
			}
			ResultSet rs = null;
			rs = st.executeQuery();

			while(rs!=null && rs.next()){
				if(username.equals(rs.getString(2))){
					if(password.equals(rs.getString(3))) { 					
						return getUserObjectFromResultSet(rs);
					}
					else
					{
						return null;
					}
				}

			}
		}
		return null;
	}
	
	private Customer getUserObjectFromResultSet(ResultSet rs) throws SQLException {
		return new Customer(rs.getString(2), rs.getInt(1));
	}

}
