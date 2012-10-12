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
import com.team8.responses.BondSearchResponse;

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
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("username"));
		if(session.getAttribute("username") == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		
		String cusip = (String)request.getParameter("cusip");
		Gson gson = new Gson();

		if(cusip != null) {
			List<Bond> bonds = new ArrayList<Bond>();
			bonds.add(new Bond(cusip));
			System.out.println(request.getSession().getAttribute("username") +" buys "+ cusip);
			BondSearchResponse bsr = new BondSearchResponse(bonds, 200, "OK");
			out.print(gson.toJson(bsr));
			out.close();
		}

		out.print(gson.toJson(new BondSearchResponse(bondsList, 200, "OK")));
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
