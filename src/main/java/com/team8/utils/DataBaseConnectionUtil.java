package com.team8.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnectionUtil {

	private static Connection databaseConnection ;
	static {		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blbdata","root","dbdb123");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	private DataBaseConnectionUtil() {

	}

	public static Connection getDatabaseConnection() {
		return databaseConnection;
	}

}
