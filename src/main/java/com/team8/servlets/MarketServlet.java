package com.team8.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.team8.model.Bond;

/**
 * Servlet implementation class Market
 */
@WebServlet("/Market")
public class MarketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Bond> bondsList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarketServlet() {
		super();

		bondsList = new ArrayList<Bond>();

		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());
		bondsList.add(new Bond());        

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");		
		PrintWriter out = response.getWriter();
		String cusip = (String)request.getParameter("cusip");
		Gson gson = new Gson();

		if(cusip != null) {
			System.out.println(request.getSession().getAttribute("username") +" buys "+ cusip);
			out.print(gson.toJson(new Bond(cusip)));
			out.close();
		}

		out.print(gson.toJson(bondsList));
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
