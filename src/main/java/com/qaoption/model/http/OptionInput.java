package com.qaoption.model.http;

import java.math.BigDecimal;

public class OptionInput {

	String longShort;
	String stockCode; // HSI, HHI, 0005.HK 
	String contractType; // FUT or OOP
	BigDecimal strike;
	java.util.Date matDate;
	int qty;
	String callPut;
	BigDecimal premium;
	double iv;
    
    public OptionInput() {}

	public String getLongShort() {
		return longShort;
	}

	public void setLongShort(String longShort) {
		this.longShort = longShort;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public BigDecimal getStrike() {
		return strike;
	}

	public void setStrike(BigDecimal strike) {
		this.strike = strike;
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

	public String getCallPut() {
		return callPut;
	}

	public void setCallPut(String callPut) {
		this.callPut = callPut;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public double getIv() {
		return iv;
	}

	public void setIv(double iv) {
		this.iv = iv;
	}
    

}