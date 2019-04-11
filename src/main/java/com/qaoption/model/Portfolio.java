package com.qaoption.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	String stockCode;
	List<OptionContract> options =  new ArrayList<OptionContract>();
	List<OptionContract> futures = new ArrayList<OptionContract>();
	
	public void addOption(OptionContract c) {
		if (options == null)
			options = new ArrayList<OptionContract>();
		
		options.add(c);
	}
	
	public void addFuture(OptionContract c) {
		if (futures == null)
			futures = new ArrayList<OptionContract>();
		
		futures.add(c);
	}
	
	public List<OptionContract> getOptions() {
		return options;
	}
	public void setOptions(List<OptionContract> options) {
		this.options = options;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public List<OptionContract> getFutures() {
		return futures;
	}

	public void setFutures(List<OptionContract> futures) {
		this.futures = futures;
	}
	
}
