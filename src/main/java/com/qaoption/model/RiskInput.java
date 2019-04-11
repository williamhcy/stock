package com.qaoption.model;

public class RiskInput implements Cloneable {
	// Type 0 
	public String businessDate;
	
	// type 2
	public String riskExponent, contractStockCode, contractType;
	
	// type C
	public String intraCommoditySpreadCharge;
	
	// type 4
	public String shortOptionMinCharge;
	
	// 81
	public String callput, optionContractMonth, optionStrike, riskArray1, riskArray2, riskArray3, riskArray4;
	public String riskArray5, riskArray6, riskArray7, riskArray8, riskArray9;
	
	// 82
	public String riskArray10, riskArray11, riskArray12, riskArray13, riskArray14;
	public String riskArray15, riskArray16, compositeDelta, iv, settelementPrice;
	
	public RiskInput clone() throws CloneNotSupportedException {
        return (RiskInput) super.clone();
    }

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getRiskExponent() {
		return riskExponent;
	}

	public void setRiskExponent(String riskExponent) {
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

	public String getIntraCommoditySpreadCharge() {
		return intraCommoditySpreadCharge;
	}

	public void setIntraCommoditySpreadCharge(String intraCommoditySpreadCharge) {
		this.intraCommoditySpreadCharge = intraCommoditySpreadCharge;
	}

	public String getShortOptionMinCharge() {
		return shortOptionMinCharge;
	}

	public void setShortOptionMinCharge(String shortOptionMinCharge) {
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

	public String getOptionStrike() {
		return optionStrike;
	}

	public void setOptionStrike(String optionStrike) {
		this.optionStrike = optionStrike;
	}

	public String getRiskArray1() {
		return riskArray1;
	}

	public void setRiskArray1(String riskArray1) {
		this.riskArray1 = riskArray1;
	}

	public String getRiskArray2() {
		return riskArray2;
	}

	public void setRiskArray2(String riskArray2) {
		this.riskArray2 = riskArray2;
	}

	public String getRiskArray3() {
		return riskArray3;
	}

	public void setRiskArray3(String riskArray3) {
		this.riskArray3 = riskArray3;
	}

	public String getRiskArray4() {
		return riskArray4;
	}

	public void setRiskArray4(String riskArray4) {
		this.riskArray4 = riskArray4;
	}

	public String getRiskArray5() {
		return riskArray5;
	}

	public void setRiskArray5(String riskArray5) {
		this.riskArray5 = riskArray5;
	}

	public String getRiskArray6() {
		return riskArray6;
	}

	public void setRiskArray6(String riskArray6) {
		this.riskArray6 = riskArray6;
	}

	public String getRiskArray7() {
		return riskArray7;
	}

	public void setRiskArray7(String riskArray7) {
		this.riskArray7 = riskArray7;
	}

	public String getRiskArray8() {
		return riskArray8;
	}

	public void setRiskArray8(String riskArray8) {
		this.riskArray8 = riskArray8;
	}

	public String getRiskArray9() {
		return riskArray9;
	}

	public void setRiskArray9(String riskArray9) {
		this.riskArray9 = riskArray9;
	}

	public String getRiskArray10() {
		return riskArray10;
	}

	public void setRiskArray10(String riskArray10) {
		this.riskArray10 = riskArray10;
	}

	public String getRiskArray11() {
		return riskArray11;
	}

	public void setRiskArray11(String riskArray11) {
		this.riskArray11 = riskArray11;
	}

	public String getRiskArray12() {
		return riskArray12;
	}

	public void setRiskArray12(String riskArray12) {
		this.riskArray12 = riskArray12;
	}

	public String getRiskArray13() {
		return riskArray13;
	}

	public void setRiskArray13(String riskArray13) {
		this.riskArray13 = riskArray13;
	}

	public String getRiskArray14() {
		return riskArray14;
	}

	public void setRiskArray14(String riskArray14) {
		this.riskArray14 = riskArray14;
	}

	public String getRiskArray15() {
		return riskArray15;
	}

	public void setRiskArray15(String riskArray15) {
		this.riskArray15 = riskArray15;
	}

	public String getRiskArray16() {
		return riskArray16;
	}

	public void setRiskArray16(String riskArray16) {
		this.riskArray16 = riskArray16;
	}

	public String getCompositeDelta() {
		return compositeDelta;
	}

	public void setCompositeDelta(String compositeDelta) {
		this.compositeDelta = compositeDelta;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getSettelementPrice() {
		return settelementPrice;
	}

	public void setSettelementPrice(String settelementPrice) {
		this.settelementPrice = settelementPrice;
	}
	
	
	
}
