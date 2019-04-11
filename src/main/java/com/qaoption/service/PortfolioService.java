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
public class PortfolioService {
		
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private ContractMapper contractMapper;
	
	public  void addContract(OptionContract contract) {
	
  		try{
  			contractMapper.addContract(contract);

  		}finally{
   	
  		}
 
	}
	
	public  Map<String, Portfolio> getPortfolioByUserId(int userId) {
		Map<String, Portfolio> map = new HashMap<String, Portfolio>();
		//SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
  		try{
  		//	ContractMapper contractMapper = sqlSession.getMapper(ContractMapper.class);
  			List<OptionContract> optionlist = contractMapper.findOptionContractByUserId(userId);
  			List<OptionContract> futurelist = contractMapper.findFutureContractByUserId(userId);
  			String sc = "";
  			List<OptionContract> list = new ArrayList<OptionContract>();
  			for (OptionContract c : optionlist) {
  				String code = c.getStockCode();
  				if (!sc.equals(code)) {
  					list = new ArrayList<OptionContract>();
  					Portfolio portfolio = new Portfolio();
  					portfolio.setStockCode(code);
  					portfolio.setOptions(list);
  					map.put(c.getStockCode(), portfolio);
  					sc = code;
  				}
  				list.add(c);
  			}
  			
  			logger.debug("optionList:" + optionlist.size());
  			
  			sc = "";
  			for (OptionContract c : futurelist) {
  				String code = c.getStockCode();
  				if (!sc.equals(code)) {
  					list = new ArrayList<OptionContract>();
  					Portfolio portfolio = map.get(c.getStockCode());
  					if (portfolio == null) {
  						portfolio = new Portfolio();
  						portfolio.setStockCode(code);
  						map.put(c.getStockCode(), portfolio);
  					}
  					portfolio.setFutures(list);
  					
  					sc = code;
  				}
  				list.add(c);
  			}
  		  			
  		}finally{
   		//	sqlSession.close();
  		}
 
		return map;
	}
	
	
}
