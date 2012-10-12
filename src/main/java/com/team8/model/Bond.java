package com.team8.model;

import java.util.*;

import com.team8.utils.EncryptionUtil;

public class Bond {
	
	private String cusip;
	private String rating;
	private double coupon, price, parValue;
	private double yieldToMaturity, currentYield;
	private Date maturityDate;
	private int quantityAvailable;
	
	public Bond() {
		
		cusip = EncryptionUtil.md5("" + (Math.random() * 100000 + 1000));
		cusip = cusip.substring(0, 9);		
		rating = "AAA";		
		parValue = 100;
		coupon = Math.round((Math.random() * 0.15 + 0.01) * 100) / 100.0;
		price = Math.round((Math.random() * 10 + 95) * 100000) / 100000.0;
		quantityAvailable = (int) (Math.random() * 50) * 5;
		maturityDate = new Date();				
	}
	
	public Bond(String cusip) {
		this.cusip = cusip;
		rating = "AAA";		
		parValue = 100;
		coupon = Math.round((Math.random() * 0.15 + 0.01) * 100) / 100.0;
		price = Math.round((Math.random() * 10 + 95) * 100000) / 100000.0;
		quantityAvailable = (int) (Math.random() * 50) * 5;
		maturityDate = new Date();
	}

}
