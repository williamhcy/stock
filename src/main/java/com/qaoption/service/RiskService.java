package com.qaoption.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaoption.mapper.ContractMapper;
import com.qaoption.mapper.RiskMapper;
import com.qaoption.model.OptionContract;
import com.qaoption.model.Portfolio;
import com.qaoption.model.RiskInput;
import com.qaoption.model.RiskParameter;

@Service
public class RiskService {
		
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private RiskMapper riskMapper;
	
	@Autowired
	private ContractMapper contractMapper;
	
	public  RiskParameter getRisk(OptionContract contract) {
		RiskParameter risk = null;
		//SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
  		try{
  			//RiskMapper riskMapper = sqlSession.getMapper(RiskMapper.class);
  			if ("FUT".equals(contract.getContractType())) {
  				OptionContract c = new OptionContract();
  				c.setContractType("FUT");
  				c.setStockCode(contract.getStockCode());
  				risk = riskMapper.findByContract(c);
  			} else {
  				risk = riskMapper.findByContract(contract);
  			}
  			//  			sqlSession.commit();
  		}finally{
   		//	sqlSession.close();
  		}
 
		return risk;
	}
	
	
	public  List<BigDecimal> findStrikeSpreadByStockCode(String stockCode) {
		List<BigDecimal> list = null;
		//SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
  		try{
  		//	RiskMapper riskMapper = sqlSession.getMapper(RiskMapper.class);
  			list = riskMapper.findStrikeSpreadByStockCode(stockCode);
  			
  		}finally{
   		//	sqlSession.close();
  		}
 
		return list;
	}
	
	
	public void addRiskParameter(HashMap<String, RiskInput> rpMap) {
		
		for (Map.Entry<String, RiskInput> entry : rpMap.entrySet()) {
		    String key = entry.getKey();
		    RiskInput value = entry.getValue();
		   	
		    logger.debug(key +"]risk:"+value);
		    if (value.optionContractMonth == null && value.contractType.equals("OOP")) {
		    } else if (value.riskArray9 == null) {
		    } else {
		    	logger.debug("strike:"+value.optionStrike);
		    	riskMapper.addRiskParameter(value);
		    }
		}
	}
	

}
