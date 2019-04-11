package com.qaoption.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Contract {
	
	String stockCode; // HSI, HHI, 0005.HK 
	String contractType; // FUT or OOP

	BigDecimal strike;
	java.util.Date matDate;
	int qty;
	
	int user_id;
	
	


	public String getContractMonth() {
		if (matDate != null) {
			DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
			return format.format(matDate).substring(0,6);
		}
		return null;
	}
	

	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public java.util.Date getMatDate() {
		return matDate;
	}
	public void setMatDate(java.util.Date matDate) {
		this.matDate = matDate;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public BigDecimal getStrike() {
		return strike;
	}
	public void setStrike(BigDecimal strike) {
		this.strike = strike;
	}
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
