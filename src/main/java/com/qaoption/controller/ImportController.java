package com.qaoption.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qaoption.loading.ImportMarginRiskParameter;
import com.qaoption.model.Portfolio;
import com.qaoption.model.RiskInput;
import com.qaoption.service.RiskService;


@RestController
@EnableAutoConfiguration
@RequestMapping("/import")
public class ImportController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	
    @Autowired
    private RiskService riskService;
    

	/**
	 * Use to import the RiskParameter from HKEX
	 * @return
	 */
    @RequestMapping("/riskparameter")
    public String loadingRiskFileToDB() {
    	String riskFilePath = "./externaldata/rpf______-_____-__-_____-190304.lis";
    	// download the risk file from HKEX http
    	
    	
    	// load the risk file to DB
    	
		ImportMarginRiskParameter importRp = new ImportMarginRiskParameter();
		HashMap<String, RiskInput> rpMap = importRp.loadingRiskFile(riskFilePath);
    	
    	riskService.addRiskParameter(rpMap);
    	
    	return "import Risk Parameter success";
    	
    }


}

