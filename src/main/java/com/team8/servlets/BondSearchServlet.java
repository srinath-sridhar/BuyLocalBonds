package com.team8.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team8.dao.BondSearchDao;
import com.team8.responses.BondSearchResponse;

/**
 * Servlet implementation class BondSearchServlet
 */
@WebServlet("/BondSearch")
public class BondSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BondSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BondSearchDao dao = new BondSearchDao();
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("application/json");
			BondSearchResponse bsr = dao.searchBonds(request.getParameterMap());
			Gson gson = new Gson();
			out.print(gson.toJson(bsr));
			out.close();
		} catch (Exception e) {
			out.close();
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Map<String, String[]> searchParams = request.getParameterMap();
		doGet(request, response);
		
	}

}
