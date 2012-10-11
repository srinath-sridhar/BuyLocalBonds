package com.team8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.team8.utils.DataBaseConnectionUtil;

public class UserAuthenticationDao {

	private Connection databaseConnection = null;
	
	public UserAuthenticationDao() {
		databaseConnection = DataBaseConnectionUtil.getDatabaseConnection();
	}

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	public boolean isUserAuthenticated(HttpServletRequest request) throws SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return isUserAuthenticated(username, password);
		
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
