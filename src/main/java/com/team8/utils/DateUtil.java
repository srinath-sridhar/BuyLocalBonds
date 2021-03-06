package com.team8.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;

public class DateUtil {
	
	@SuppressWarnings("deprecation")
	public static Date convertStringToDate(String inputDate) {
		java.util.Date dt;

		java.text.DateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd");
 
		try {
			dt = sdf.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		java.sql.Date date = new Date(dt.getYear(), dt.getMonth(), dt.getDate());
		return date;
				
	}
	
	public static Date convertLongToDate(long inputDate) {
		java.util.Date dt;
 
		try {
			dt = new Date(inputDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		@SuppressWarnings("deprecation")
		java.sql.Date date = new Date(dt.getYear(), dt.getMonth(), dt.getDate());
		return date;
	}
	
}
