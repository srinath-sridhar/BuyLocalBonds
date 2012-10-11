package com.team8.model;

import java.util.*;

import com.team8.utils.EncryptionUtil;

public class Bond {
	
	String cusip, rating;
	double coupon, price;
	Date maturityDate;
	int quantityAvailable;
	
	public Bond() {
		
		cusip = EncryptionUtil.md5("" + (Math.random() * 100000 + 1000));
		cusip = cusip.substring(0, 9);		
		rating = "AAA";		
		coupon = Math.round((Math.random() * 0.15 + 0.01) * 100) / 100.0;
		price = Math.round((Math.random() * 10 + 95) * 100000) / 100000.0;
		quantityAvailable = (int) (Math.random() * 50) * 5;
		maturityDate = new Date();				
	}

}
