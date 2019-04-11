package com.qaoption.model;

import java.math.BigDecimal;

public class RiskParameter {
	// Type 0 
	public String businessDate;
	
	// type 2
	public int riskExponent;
	public String contractStockCode, contractType;
	
	// type C
	public int intraCommoditySpreadCharge;
	
	//type 4
	public int shortOptionMinCharge;
	
	// 81
	public String callput, optionContractMonth;
	public BigDecimal optionStrike; 
	public int riskArray1, riskArray2, riskArray3, riskArray4;
	public int riskArray5, riskArray6, riskArray7, riskArray8, riskArray9;
	
	// 82
	public int riskArray10, riskArray11, riskArray12, riskArray13, riskArray14;
	public int riskArray15, riskArray16; 
	public double compositeDelta, iv, settelementPrice;
	
	
	public String getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}
	public int getRiskExponent() {
		return riskExponent;
	}
	public void setRiskExponent(int riskExponent) {
		this.riskExponent = riskExponent;
	}
	public String getContractStockCode() {
		return contractStockCode;
	}
	public void setContractStockCode(String contractStockCode) {
		this.contractStockCode = contractStockCode;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public int getIntraCommoditySpreadCharge() {
		return intraCommoditySpreadCharge;
	}
	public void setIntraCommoditySpreadCharge(int intraCommoditySpreadCharge) {
		this.intraCommoditySpreadCharge = intraCommoditySpreadCharge;
	}
	public int getShortOptionMinCharge() {
		return shortOptionMinCharge;
	}
	public void setShortOptionMinCharge(int shortOptionMinCharge) {
		this.shortOptionMinCharge = shortOptionMinCharge;
	}

	public String getCallput() {
		return callput;
	}
	public void setCallput(String callput) {
		this.callput = callput;
	}
	public String getOptionContractMonth() {
		return optionContractMonth;
	}
	public void setOptionContractMonth(String optionContractMonth) {
		this.optionContractMonth = optionContractMonth;
	}
	public BigDecimal getOptionStrike() {
		return optionStrike;
	}
	public void setOptionStrike(BigDecimal optionStrike) {
		this.optionStrike = optionStrike;
	}
	public int getRiskArray1() {
		return riskArray1;
	}
	public void setRiskArray1(int riskArray1) {
		this.riskArray1 = riskArray1;
	}
	public int getRiskArray2() {
		return riskArray2;
	}
	public void setRiskArray2(int riskArray2) {
		this.riskArray2 = riskArray2;
	}
	public int getRiskArray3() {
		return riskArray3;
	}
	public void setRiskArray3(int riskArray3) {
		this.riskArray3 = riskArray3;
	}
	public int getRiskArray4() {
		return riskArray4;
	}
	public void setRiskArray4(int riskArray4) {
		this.riskArray4 = riskArray4;
	}
	public int getRiskArray5() {
		return riskArray5;
	}
	public void setRiskArray5(int riskArray5) {
		this.riskArray5 = riskArray5;
	}
	public int getRiskArray6() {
		return riskArray6;
	}
	public void setRiskArray6(int riskArray6) {
		this.riskArray6 = riskArray6;
	}
	public int getRiskArray7() {
		return riskArray7;
	}
	public void setRiskArray7(int riskArray7) {
		this.riskArray7 = riskArray7;
	}
	public int getRiskArray8() {
		return riskArray8;
	}
	public void setRiskArray8(int riskArray8) {
		this.riskArray8 = riskArray8;
	}
	public int getRiskArray9() {
		return riskArray9;
	}
	public void setRiskArray9(int riskArray9) {
		this.riskArray9 = riskArray9;
	}
	public int getRiskArray10() {
		return riskArray10;
	}
	public void setRiskArray10(int riskArray10) {
		this.riskArray10 = riskArray10;
	}
	public int getRiskArray11() {
		return riskArray11;
	}
	public void setRiskArray11(int riskArray11) {
		this.riskArray11 = riskArray11;
	}
	public int getRiskArray12() {
		return riskArray12;
	}
	public void setRiskArray12(int riskArray12) {
		this.riskArray12 = riskArray12;
	}
	public int getRiskArray13() {
		return riskArray13;
	}
	public void setRiskArray13(int riskArray13) {
		this.riskArray13 = riskArray13;
	}
	public int getRiskArray14() {
		return riskArray14;
	}
	public void setRiskArray14(int riskArray14) {
		this.riskArray14 = riskArray14;
	}
	public int getRiskArray15() {
		return riskArray15;
	}
	public void setRiskArray15(int riskArray15) {
		this.riskArray15 = riskArray15;
	}
	public int getRiskArray16() {
		return riskArray16;
	}
	public void setRiskArray16(int riskArray16) {
		this.riskArray16 = riskArray16;
	}
	public double getCompositeDelta() {
		return compositeDelta;
	}
	public void setCompositeDelta(double compositeDelta) {
		this.compositeDelta = compositeDelta;
	}
	public double getIv() {
		return iv;
	}
	public void setIv(double iv) {
		this.iv = iv;
	}
	public double getSettelementPrice() {
		return settelementPrice;
	}
	public void setSettelementPrice(double settelementPrice) {
		this.settelementPrice = settelementPrice;
	}
	
}
