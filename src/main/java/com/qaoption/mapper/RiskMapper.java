package com.qaoption.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.qaoption.model.OptionContract;
import com.qaoption.model.RiskInput;
import com.qaoption.model.RiskParameter;
@org.apache.ibatis.annotations.Mapper
public interface RiskMapper { 
    public RiskParameter findById(String Id);
    public RiskParameter findByContract(OptionContract c);
    public void addRiskParameter(RiskInput rp);
    public List<BigDecimal> findStrikeSpreadByStockCode(String stockCode);
}
