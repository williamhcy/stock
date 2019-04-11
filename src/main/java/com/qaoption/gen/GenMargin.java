package com.qaoption.gen;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.qaoption.model.OptionContract;
import com.qaoption.model.Portfolio;
import com.qaoption.model.RiskParameter;
import com.qaoption.service.RiskService;
import com.qaoption.util.PortfolioUtil;

public class GenMargin {
	final static Logger logger = LogManager.getRootLogger();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenMargin gm = new GenMargin();
		double margin = -99999999;
		try {
			RiskService riskService = new RiskService();
			// get Portfilio from DB
			Map<String, Portfolio> portfolioMap =riskService.getPortfolioByUserId(1);
			Portfolio port = null;
			for (Map.Entry<String, Portfolio> entry : portfolioMap.entrySet()) {
			    String key = entry.getKey();
			    port = entry.getValue();
			    logger.debug("portfolio:" + port.getStockCode());
			    List<BigDecimal> strikeList = riskService.findStrikeSpreadByStockCode(port.getStockCode());
			    for (int i = -5 ; i < 5; i++) {
			    	int total = strikeList.size();
			    	int mid = total/2;
			    	BigDecimal change = strikeList.get(mid + i +1).subtract(strikeList.get(mid + i));
				    PortfolioUtil.shiftStrike(port, change);
				    margin = gm.genPortfolioMargin(port);
				    logger.debug("margin:" + margin);
			    }
			}		
			
			
		
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	// testing
	public double genPortfolioMargin(Portfolio portfolio) throws Exception {
		
		// found out the max risk array value
		RiskParameter riskArrayInTotal = new RiskParameter();
		
		for (OptionContract option: portfolio.getOptions()) {
			
			RiskParameter risk = RiskService.getRisk(option); 
			riskArrayInTotal.riskArray1 += risk.riskArray1 * option.getQty();
			riskArrayInTotal.riskArray2 += risk.riskArray2 * option.getQty();
			riskArrayInTotal.riskArray3 += risk.riskArray3 * option.getQty();
			riskArrayInTotal.riskArray4 += risk.riskArray4 * option.getQty();
			riskArrayInTotal.riskArray5 += risk.riskArray5 * option.getQty();
			riskArrayInTotal.riskArray6 += risk.riskArray6 * option.getQty();
			riskArrayInTotal.riskArray7 += risk.riskArray7 * option.getQty();
			riskArrayInTotal.riskArray8 += risk.riskArray8 * option.getQty();
			riskArrayInTotal.riskArray9 += risk.riskArray9 * option.getQty();
			riskArrayInTotal.riskArray10 += risk.riskArray10 * option.getQty();
			riskArrayInTotal.riskArray11 += risk.riskArray11 * option.getQty();
			riskArrayInTotal.riskArray12 += risk.riskArray12 * option.getQty();
			riskArrayInTotal.riskArray13 += risk.riskArray13 * option.getQty();
			riskArrayInTotal.riskArray14 += risk.riskArray14 * option.getQty();
			riskArrayInTotal.riskArray15 += risk.riskArray15 * option.getQty();
			riskArrayInTotal.riskArray16 += risk.riskArray16 * option.getQty();
			
			riskArrayInTotal.intraCommoditySpreadCharge = risk.intraCommoditySpreadCharge;
			riskArrayInTotal.shortOptionMinCharge = risk.shortOptionMinCharge;
			
		}
		
		for (OptionContract future: portfolio.getFutures()) {
			RiskParameter risk = RiskService.getRisk(future);
			riskArrayInTotal.riskArray1 += risk.riskArray1 * future.getQty();
			riskArrayInTotal.riskArray2 += risk.riskArray2 * future.getQty();
			riskArrayInTotal.riskArray3 += risk.riskArray3 * future.getQty();
			riskArrayInTotal.riskArray4 += risk.riskArray4 * future.getQty();
			riskArrayInTotal.riskArray5 += risk.riskArray5 * future.getQty();
			riskArrayInTotal.riskArray6 += risk.riskArray6 * future.getQty();
			riskArrayInTotal.riskArray7 += risk.riskArray7 * future.getQty();
			riskArrayInTotal.riskArray8 += risk.riskArray8 * future.getQty();
			riskArrayInTotal.riskArray9 += risk.riskArray9 * future.getQty();
			riskArrayInTotal.riskArray10 += risk.riskArray10 * future.getQty();
			riskArrayInTotal.riskArray11 += risk.riskArray11 * future.getQty();
			riskArrayInTotal.riskArray12 += risk.riskArray12 * future.getQty();
			riskArrayInTotal.riskArray13 += risk.riskArray13 * future.getQty();
			riskArrayInTotal.riskArray14 += risk.riskArray14 * future.getQty();
			riskArrayInTotal.riskArray15 += risk.riskArray15 * future.getQty();
			riskArrayInTotal.riskArray16 += risk.riskArray16 * future.getQty();
			
			riskArrayInTotal.intraCommoditySpreadCharge = risk.intraCommoditySpreadCharge;
		}
		
		// result of this section
		// find the the max array value
		int maxRiskArrayValue = getMaxRiskValue(riskArrayInTotal);
		
		
		// ===========================================
		// Intracommodity Spread Charge
		// 1. find out how many different calend month on all contract
		HashMap<String, Double> map = new HashMap<String, Double>();
		for (OptionContract option: portfolio.getOptions()) {
			RiskParameter p = RiskService.getRisk(option);
			Double delta = new Double(p.compositeDelta * option.getQty());
			map.put(option.getContractMonth(), delta);
		}
		for (OptionContract future: portfolio.getFutures()) {
			RiskParameter p = RiskService.getRisk(future);
			Double delta = new Double(p.compositeDelta * future.getQty());
			map.put(future.getContractMonth(), delta);

		}
		// find out the minium value of delta * intraCommoditySpreadCharge
		double mini = -1;
		for (Map.Entry<String, Double> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Double value = entry.getValue();
		    double absDelta = java.lang.Math.abs(value.doubleValue());
		    if (absDelta > mini) 
		    	mini = absDelta;
		}
		// result of this section
		double maxIntracommoditySpreadCharge = mini * riskArrayInTotal.intraCommoditySpreadCharge;
		
		//=================================
		// Spot Month Charge
		
		
		//==================================================
		// Short Option Minimum Charge
		int qtyInShort = 0;
		for (OptionContract option: portfolio.getOptions()) {
			if (option.getQty() < 0) {
				qtyInShort = qtyInShort + Math.abs(option.getQty());
			}
		}
		
		int shortOptionMinCharge = riskArrayInTotal.shortOptionMinCharge * qtyInShort; 
		
		double netMargin = maxRiskArrayValue + maxIntracommoditySpreadCharge;
		if (maxRiskArrayValue + maxIntracommoditySpreadCharge < shortOptionMinCharge)
			netMargin = shortOptionMinCharge;
		 
		
		return netMargin; 
		
		// Intercommodity spread credit... (not calc)
		
		
		
		
	}
	
	
	public int getMaxRiskValue(RiskParameter risk) {
		int maxValue = -999999999;
		if (risk.riskArray1 > maxValue) {
			maxValue = risk.riskArray1;
		}
		if (risk.riskArray2 > maxValue) {
			maxValue = risk.riskArray2;
		}
		if (risk.riskArray3 > maxValue) {
			maxValue = risk.riskArray3;
		}
		if (risk.riskArray4 > maxValue) {
			maxValue = risk.riskArray4;
		}
		if (risk.riskArray5 > maxValue) {
			maxValue = risk.riskArray5;
		}
		if (risk.riskArray6 > maxValue) {
			maxValue = risk.riskArray6;
		}
		if (risk.riskArray7 > maxValue) {
			maxValue = risk.riskArray7;
		}
		if (risk.riskArray8 > maxValue) {
			maxValue = risk.riskArray8;
		}
		if (risk.riskArray9 > maxValue) {
			maxValue = risk.riskArray9;
		}
		if (risk.riskArray10 > maxValue) {
			maxValue = risk.riskArray10;
		}
		if (risk.riskArray11 > maxValue) {
			maxValue = risk.riskArray11;
		}
		if (risk.riskArray12 > maxValue) {
			maxValue = risk.riskArray12;
		}
		if (risk.riskArray13 > maxValue) {
			maxValue = risk.riskArray13;
		}
		if (risk.riskArray14 > maxValue) {
			maxValue = risk.riskArray14;
		}
		if (risk.riskArray15 > maxValue) {
			maxValue = risk.riskArray15;
		}
		if (risk.riskArray16 > maxValue) {
			maxValue = risk.riskArray16;
		}
		
		return maxValue;
		
	}

}
