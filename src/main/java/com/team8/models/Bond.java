package com.team8.models;

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
	

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public double getCoupon() {
		return coupon;
	}

	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public double getYieldToMaturity() {
		return yieldToMaturity;
	}

	public void setYieldToMaturity(double yieldToMaturity) {
		this.yieldToMaturity = yieldToMaturity;
	}

	public double getCurrentYield() {
		return currentYield;
	}

	public void setCurrentYield(double currentYield) {
		this.currentYield = currentYield;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}



}
