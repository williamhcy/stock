package com.qaoption.model;

import java.math.BigDecimal;
import java.util.Date;

public class OptionResult {

	// variable
	Date resultDate;
	BigDecimal underlyingPriceLevel;
	
	// output
	Portfolio portfolio;
	double marginLevel;
	double profitLoss;
	
	
	public double getMarginLevel() {
		return marginLevel;
	}
	public void setMarginLevel(double marginLevel) {
		this.marginLevel = marginLevel;
	}
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	public double getProfitLoss() {
		return profitLoss;
	}
	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}
	public Date getResultDate() {
		return resultDate;
	}
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	public BigDecimal getUnderlyingPriceLevel() {
		return underlyingPriceLevel;
	}
	public void setUnderlyingPriceLevel(BigDecimal underlyingPriceLevel) {
		this.underlyingPriceLevel = underlyingPriceLevel;
	}
	
	
}
