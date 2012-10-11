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

		ResultSet rs = null;

		try {
			if (username == null || password == null) {
				pw.print("fail");
				pw.close();
				return;
			}

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blbdata","root","");
			Statement st = conn.createStatement();
			rs = st.executeQuery("select * from accounts where UserName = '" + username + "'");


			while(rs!=null && rs.next()){
				if(username.equals(rs.getString(2))){
					if(password.equals(rs.getString(3))) { 
						//response.setContentType("application/json");						
						HttpSession session = request.getSession();
						session.setAttribute("user", rs.getInt(1));
						pw.write("success");
						pw.close();
						return;
					}
					else
					{
						//response.sendRedirect("/BLB8");
						pw.write("fail");
						pw.close();
						return;
					}
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		//request.getRequestDispatcher("/").forward(request, response);
		pw.write("fail");
		pw.close();		
	}

}
