package com.qaoption.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class OptionContract extends Contract {

	
	String callPut;
	BigDecimal premium;
	BigDecimal interest;
	double iv;
	
	OptionGreeks greeks;
	
	public String getContractMonth() {
		if (matDate != null) {
			DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
			return format.format(matDate).substring(0,6);
		}
		return null;
	}
	
	public String getCallPut() {
		return callPut;
	}
	public void setCallPut(String callPut) {
		this.callPut = callPut;
	}
	public double getIv() {
		return iv;
	}
	public void setIv(double iv) {
		this.iv = iv;
	}

	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public OptionGreeks getGreeks() {
		return greeks;
	}
	public void setGreeks(OptionGreeks greeks) {
		this.greeks = greeks;
	}
	
	
}
