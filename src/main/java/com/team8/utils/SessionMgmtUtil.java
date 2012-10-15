package com.team8.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMgmtUtil {
	
	public static boolean checkUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(username == null) {
			return false;
		}
		else {
			return true;
		}
	}

}
