package com.qaoption.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.qaoption.model.OptionContract;
import com.qaoption.model.Portfolio;
import com.qaoption.model.http.OptionInput;
import com.qaoption.service.PortfolioService;
import com.qaoption.service.RiskService;


@RestController
@EnableAutoConfiguration
@RequestMapping("/option")
public class OptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	
    @Autowired
    private RiskService riskService;
    
    @Autowired
    private PortfolioService portfolioService;

    @RequestMapping("/testing")
    public String testQuery() {
    	
    	Map<String, Portfolio> portfolioMap = portfolioService.getPortfolioByUserId(1);
    	String str = "map:";
    	if (portfolioMap != null) {
			for (Map.Entry<String, Portfolio> entry : portfolioMap.entrySet()) {
			    String key = entry.getKey();
			    Portfolio port = entry.getValue();
			    logger.debug("portfolio:" + port.getStockCode());
			    str = str + key + port.getStockCode();
			}	
			return str;
    	} else {
    		 return "null result";
    	}
    }

  
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},value="/addOption")
    @ResponseBody
    public OptionContract addOption(@RequestBody OptionInput optionInput) {
   
    	logger.debug("OptionController.addOption.getStockCode : "+ optionInput.getStockCode());
    	logger.debug("OptionController.addOption.getStrike : "+ optionInput.getStrike());
    	
    	OptionContract c = new OptionContract();
    	c.setCallPut(optionInput.getCallPut());
    	c.setContractType(optionInput.getContractType());
    	c.setIv(optionInput.getIv());
    	c.setPremium(optionInput.getPremium());
    	c.setMatDate(optionInput.getMatDate());
    	c.setQty(optionInput.getQty());
    	c.setStockCode(optionInput.getStockCode());
    	c.setStrike(optionInput.getStrike());
    	
    	//portfolioService.addContract(c);
    	
    	return c;
    	
    }


    @RequestMapping(value="/getportfolio", method=RequestMethod.GET)
    @ResponseBody
    public List<OptionContract> getPortfolio() {
    	List<OptionContract> list = new ArrayList<OptionContract>();
    	Map<String, Portfolio> portfolioMap = portfolioService.getPortfolioByUserId(1);
    	if (portfolioMap != null) {
			for (Map.Entry<String, Portfolio> entry : portfolioMap.entrySet()) {
			    String key = entry.getKey();
			    Portfolio port = entry.getValue();
			    logger.debug("portfolio:" + port.getStockCode());
			    list.addAll(port.getFutures());
			    list.addAll(port.getOptions());
			}	
			
    	} else {
    		 return null;
    	}
    	
    	// return the list JSON object to the Requester
    	
    	return list;
    	
    }
    
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView list(){
        List<String> learnList =new ArrayList<String>();
       
      
        learnList.add("data1");  
        learnList.add("data2");
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }
    
    
    @RequestMapping(value="/portfolio", method=RequestMethod.GET)
    public ModelAndView portfolio(){
    	List<OptionContract> list = new ArrayList<OptionContract>();
    	Map<String, Portfolio> portfolioMap = portfolioService.getPortfolioByUserId(1);
    	if (portfolioMap != null) {
			for (Map.Entry<String, Portfolio> entry : portfolioMap.entrySet()) {
			    String key = entry.getKey();
			    Portfolio port = entry.getValue();
			    logger.debug("portfolio:" + port.getStockCode());
			    list.addAll(port.getFutures());
			    list.addAll(port.getOptions());
			}	
			
    	} 
        ModelAndView modelAndView = new ModelAndView("/portfolio");
        modelAndView.addObject("portfolioMap", list);
        return modelAndView;
    }
}

