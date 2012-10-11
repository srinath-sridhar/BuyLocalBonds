package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticationDao {

	private Connection databaseConnection = null;

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	public void setDatabaseConnection(Connection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	public boolean isUserAuthenticated(String username, String password) throws SQLException {
		if(databaseConnection == null) {
			return false;
		}
		if(username == null || password == null) {
			return false;
		}

		String sql = "SELECT * FROM accounts where UserName = ? ";
		PreparedStatement st = databaseConnection.prepareStatement(sql);
		st.setString(1, username);
		ResultSet rs = null;
		rs = st.executeQuery();

		while(rs!=null && rs.next()){
			if(username.equals(rs.getString(2))){
				if(password.equals(rs.getString(3))) { 					
					return true;
				}
				else
				{
					return false;
				}
			}

		}
		return false;
	}

}
