package com.team8.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.team8.dao.BuyBondDao;
import com.team8.responses.OrderResponse;
import com.team8.utils.SessionMgmtUtil;

/**
 * Servlet implementation class BuyBond
 */
public class BuyBondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyBondServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		BuyBondDao dao = new BuyBondDao();
		OrderResponse bor = null;
		int traderId, customerId;
		
		if(!SessionMgmtUtil.checkUserLoggedIn(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		try {
			response.setContentType("application/json");
			
			traderId = (int) session.getAttribute("userId");
			customerId = (int) session.getAttribute("currentCustomer");
			bor = dao.buyBond(request.getParameterMap(), customerId, traderId);
			
			Gson gson = new Gson();
			out.print(gson.toJson(bor));
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
