package com.team8.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team8.dao.UserAuthenticationDao;

/**
 * Servlet implementation class UserAuthentication
 */
@WebServlet("/UserAuthentication")
public class UserAuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 23L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAuthenticationServlet() {
		super();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		PrintWriter pw = response.getWriter();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blbdata","root","");
			UserAuthenticationDao dao = new UserAuthenticationDao();
			dao.setDatabaseConnection(conn);
			if(dao.isUserAuthenticated(username, password))
			{
				pw.write("success");
			}
			else {
				pw.write("fail");
			}
			pw.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
