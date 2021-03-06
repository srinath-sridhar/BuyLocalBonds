package com.team8.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.team8.dao.PortfolioUpdateDao;
import com.team8.responses.PortfolioResponse;
import com.team8.utils.SessionMgmtUtil;

/**
 * Servlet implementation class Portfolio
 */
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PortfolioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		PortfolioUpdateDao dao = new PortfolioUpdateDao();
		PortfolioResponse pfr = null;

		int customerId;

		if(!SessionMgmtUtil.checkUserLoggedIn(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		try {
			response.setContentType("application/json");			
			customerId = (int) session.getAttribute("currentCustomer");
			pfr = dao.getCustomerPortfolio(customerId);
			Gson gson = new Gson();
			out.print(gson.toJson(pfr));
			out.close();
		}
		catch(Exception e) {
			out.close();
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
