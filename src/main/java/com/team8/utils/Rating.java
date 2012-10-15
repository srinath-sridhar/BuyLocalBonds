package com.team8.utils;

import java.util.ArrayList;
import java.util.List;

public class Rating {
	
	private static List<String> ratingOrder;
	static {
		ratingOrder = new ArrayList<String>();
		ratingOrder.add("AAA");
		ratingOrder.add("AA+");
		ratingOrder.add("AA");
		ratingOrder.add("AA-");
		ratingOrder.add("A+");
		ratingOrder.add("A");
		ratingOrder.add("A-");
		ratingOrder.add("BBB+");
		ratingOrder.add("BBB");
		ratingOrder.add("BBB-");
		ratingOrder.add("BB+");
		ratingOrder.add("BB");
		ratingOrder.add("BB-");
		ratingOrder.add("B+");
		ratingOrder.add("B");
		ratingOrder.add("B-");
		ratingOrder.add("CCC+");
		ratingOrder.add("CCC");
		ratingOrder.add("CCC-");
		ratingOrder.add("CC");
		ratingOrder.add("C");
		ratingOrder.add("D");
	}
	public static List<String> getRatingsBetween(String low, String high) {
		int min = -1, max = -1;
		min = ratingOrder.indexOf(high);
		max = ratingOrder.indexOf(low);
		if(min != -1 && max != -1 && min <= max) {
			return ratingOrder.subList(min, max+1);
			
		}
		return null;
	}
	public static int getNumRatings(String low, String high) {
		int min = -1, max = -1;
		max = ratingOrder.indexOf(low);
		min = ratingOrder.indexOf(high);
		if(min != -1 && max != -1) {
			return (max - min + 1);
		}
		return -1;
	}

}
