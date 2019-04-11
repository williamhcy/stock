package com.qaoption.loading;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.qaoption.mapper.ContractMapper;
import com.qaoption.mapper.RiskMapper;
import com.qaoption.model.OptionContract;
import com.qaoption.model.RiskInput;
import com.qaoption.model.RiskParameter;

public class ImportMarginRiskParameter {
	
	final static Logger logger = LogManager.getRootLogger();

	SqlSessionFactory sessionFactory = null;

	private static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
	
	public static void TestQuery () {
		SqlSession sqlSession = getSessionFactory().openSession();
		RiskMapper riskMapper = sqlSession.getMapper(RiskMapper.class);
		ContractMapper contractMapper = sqlSession.getMapper(ContractMapper.class);
		DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		OptionContract c1 = new OptionContract();
		c1.setContractType("OOP");;
		c1.setCallPut("P");
		c1.setStockCode("A50");
		c1.setStrike(new BigDecimal(10));
		c1.setUser_id(1);
		try {
			c1.setMatDate(format.parse("20190328"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c1.setInterest(new BigDecimal(0.02));
		c1.setPremium(new BigDecimal(100));
		c1.setQty(1);
		c1.setIv(0.20);
		
		RiskParameter risk = riskMapper.findByContract(c1);
		System.out.println(risk.optionContractMonth);
		
		contractMapper.addContract(c1);
		
		List<OptionContract> clist = contractMapper.findOptionContractByUserId(1);
		
		for (OptionContract c : clist) {
			System.out.println(c.getContractMonth() + " " + c.getCallPut());	
		}
		
	}

	public static void main(String[] args) {
		
		//TestQuery();
		
		//if (1==1)
		//	return;
		ImportMarginRiskParameter importRp = new ImportMarginRiskParameter();
		HashMap<String, RiskInput> rpMap = importRp.loadingRiskFile("./externaldata/rpf______-_____-__-_____-190304.lis");

		try {
			
			SqlSession sqlSession = getSessionFactory().openSession();
			RiskMapper riskMapper = sqlSession.getMapper(RiskMapper.class);
			
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
			
			if (sqlSession != null) {
				sqlSession.close();

			}
//			RiskParameter risk = riskMapper.findById("1");
//			
//			System.out.println(risk.optionContractMonth);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	
	public HashMap<String, RiskInput> loadingRiskFile(String filename) {
		
		HashMap<String, RiskInput> rpMap = new HashMap<String, RiskInput>();
		try {
			
			// read file from external data
			// Open the file
			FileInputStream fstream = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			RiskInput masterRI = new RiskInput();
			RiskInput rp = null, rpOption = null;
			
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				
				String type = strLine.substring(0, 2).trim();
				if ("0".equals(type))
					handleType0Line(strLine, masterRI);
				else if ("1".equals(type))
					handleType1Line(strLine, rp);
				else if ("2".equals(type)) {
					rp = new RiskInput();
					rp.businessDate = masterRI.businessDate;
					handleType2Line(strLine, rp);
					rp.contractType = "FUT";
					rpMap.put(rp.contractStockCode+"FUT", rp);
					rpOption = rp.clone();
					rpOption.contractType = "OOP";
					rpMap.put(rp.contractStockCode+"OOP", rpOption);
				}
				else if ("3".equals(type)) {
					handleType3Line(strLine, rpOption);
				}
				else if ("4".equals(type)) {
					handleType4Line(strLine, rpOption);
				}
				else if ("C".equals(type)) {
					handleTypeCLine(strLine, rpOption);
				}
				
				if ("81".equals(type)) {
					String mapContract = getType8XContractType(strLine);
					logger.debug("81mapContract:" + mapContract);
					rp = rpMap.get(mapContract);
					if (rp == null) {
						logger.debug(getType8XContractType(strLine)+ " missing");
						continue;
					}
					logger.debug("81mapContractriskExponent:" + rp.riskExponent);
					RiskInput rpOptionClone = rp.clone();
					handleType81Line(strLine, rpOptionClone);
					rpMap.put(getType8XContractType(strLine)+rpOptionClone.optionContractMonth.trim()+rpOptionClone.optionStrike.trim()+rpOptionClone.callput, rpOptionClone);
				} 
				else if ("82".equals(type)) {
					
					String mapContract = getType8XContractType(strLine);
					logger.debug("82mapContract:" + mapContract);
					rp = rpMap.get(mapContract);
					if (rp == null) {
						logger.debug(getType8XContractType(strLine)+ " missing");
						continue;
					}
					if ("1".equals(rp.riskExponent)) {
						rp = rpMap.get(getType8XContractTypeOptionX1(strLine));
					} else {
						rp = rpMap.get(getType8XContractTypeOption(strLine));
					}
					if (rp == null) {
						logger.debug(getType8XContractType(strLine)+ " missing");
						continue;
					}
					handleType82Line(strLine, rp);
				}

			}

			// Close the input stream
			fstream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return rpMap;
		
	}

	public void handleType0Line(String line, RiskInput riskp) {
		System.out.println(line);
		// get Business Date
		riskp.businessDate = getValue(line, 9, 17);
		System.out.println("line0:"+ riskp.businessDate);
	}

	public void handleType1Line(String line, RiskInput riskp) {

	}

	public void handleType2Line(String line, RiskInput riskp) {
		
		riskp.riskExponent = getValue(line, 13, 14).trim();
		riskp.contractStockCode = getValue(line, 23, 33).trim();
		riskp.contractType = getValue(line, 33, 36).trim();
		
		if ("PHY".equals(riskp.contractType)) {
			riskp.contractType = "OOP";
		}
		
		logger.debug("line2:"+ riskp.contractStockCode + ":" + riskp.contractType + "riskExponent:" + riskp.riskExponent);
	}

	public void handleType3Line(String line, RiskInput riskp) {
	}

	public void handleType4Line(String line, RiskInput riskp) {
		riskp.shortOptionMinCharge = getValue(line, 63, 70);
	}

	public void handleTypeCLine(String line, RiskInput riskp) {
		riskp.intraCommoditySpreadCharge = getValue(line, 15, 22);
	}

	public String getType8XContractType(String line) {
		return  getValue(line, 6, 16).trim() + getProductType(getValue(line, 26, 29).trim());
	}
	public String getType8XContractTypeOption(String line) {
		return  getValue(line, 6, 16).trim() + getProductType(getValue(line, 26, 29).trim())+ getValue(line, 39, 45).trim() + (getValue(line, 48, 53) + "." + getValue(line, 53, 55)).trim() + getValue(line, 29, 30);
	}
	public String getType8XContractTypeOptionX1(String line) {
		return  getValue(line, 6, 16).trim() + getProductType(getValue(line, 26, 29).trim())+ getValue(line, 39, 45).trim() + getValue(line, 48, 55) + getValue(line, 29, 30);
	}
	
	public void handleType81Line(String line, RiskInput riskp) {

		riskp.contractStockCode = getValue(line, 6, 16);
		riskp.contractType = getProductType(getValue(line, 26, 29));
		riskp.callput = getValue(line, 29, 30);
		riskp.optionContractMonth = getValue(line, 39, 45);
		if ("0".equals(riskp.riskExponent)) {
			riskp.optionStrike = getValue(line, 48, 53) + "." + getValue(line, 53, 55);
			riskp.riskArray1 = getValue(line, 60, 61) + getValue(line, 55, 60);
			riskp.riskArray2 = getValue(line, 66, 67) + getValue(line, 61, 66);
			riskp.riskArray3 = getValue(line, 72, 73) + getValue(line, 67, 72);
			riskp.riskArray4 = getValue(line, 78, 79) + getValue(line, 73, 78);
			riskp.riskArray5 = getValue(line, 84, 85) + getValue(line, 79, 84);
			riskp.riskArray6 = getValue(line, 90, 91) + getValue(line, 85, 90);
			riskp.riskArray7 = getValue(line, 96, 97) + getValue(line, 91, 96);
			riskp.riskArray8 = getValue(line, 102, 103) + getValue(line, 97, 102);
			riskp.riskArray9 = getValue(line, 108, 109) + getValue(line, 103, 108);
		} else if ("1".equals(riskp.riskExponent)) {
			riskp.optionStrike = getValue(line, 48, 55);
			riskp.riskArray1 = getValue(line, 60, 61) + getValue(line, 55, 60) +"0";
			riskp.riskArray2 = getValue(line, 66, 67) + getValue(line, 61, 66)+"0";
			riskp.riskArray3 = getValue(line, 72, 73) + getValue(line, 67, 72)+"0";
			riskp.riskArray4 = getValue(line, 78, 79) + getValue(line, 73, 78)+"0";
			riskp.riskArray5 = getValue(line, 84, 85) + getValue(line, 79, 84)+"0";
			riskp.riskArray6 = getValue(line, 90, 91) + getValue(line, 85, 90)+"0";
			riskp.riskArray7 = getValue(line, 96, 97) + getValue(line, 91, 96)+"0";
			riskp.riskArray8 = getValue(line, 102, 103) + getValue(line, 97, 102)+"0";
			riskp.riskArray9 = getValue(line, 108, 109) + getValue(line, 103, 108)+"0";
			
		}
		logger.debug("risk9" + getValue(line, 108, 109) + ":" + getValue(line, 103, 108));
		logger.debug(line);

	}

	public void handleType82Line(String line, RiskInput riskp) {

		riskp.contractStockCode = getValue(line, 6, 16);
		riskp.contractType = getProductType(getValue(line, 26, 29));
		riskp.callput = getValue(line, 29, 30);
		riskp.optionContractMonth = getValue(line, 39, 45);
		if ("0".equals(riskp.riskExponent)) {
			riskp.optionStrike = getValue(line, 48, 53) + "." + getValue(line, 53, 55);
			riskp.riskArray10 = getValue(line, 60, 61) + getValue(line, 55, 60);
			riskp.riskArray11 = getValue(line, 66, 67) + getValue(line, 61, 66);
			riskp.riskArray12 = getValue(line, 72, 73) + getValue(line, 67, 72);
			riskp.riskArray13 = getValue(line, 78, 79) + getValue(line, 73, 78);
			riskp.riskArray14 = getValue(line, 84, 85) + getValue(line, 79, 84);
			riskp.riskArray15 = getValue(line, 90, 91) + getValue(line, 85, 90);
			riskp.riskArray16 = getValue(line, 96, 97) + getValue(line, 91, 96);
		} else if ("1".equals(riskp.riskExponent)) {
			riskp.optionStrike = getValue(line, 48, 55);
			riskp.riskArray10 = getValue(line, 60, 61) + getValue(line, 55, 60)+"0";
			riskp.riskArray11 = getValue(line, 66, 67) + getValue(line, 61, 66)+"0";
			riskp.riskArray12 = getValue(line, 72, 73) + getValue(line, 67, 72)+"0";
			riskp.riskArray13 = getValue(line, 78, 79) + getValue(line, 73, 78)+"0";
			riskp.riskArray14 = getValue(line, 84, 85) + getValue(line, 79, 84)+"0";
			riskp.riskArray15 = getValue(line, 90, 91) + getValue(line, 85, 90)+"0";
			riskp.riskArray16 = getValue(line, 96, 97) + getValue(line, 91, 96)+"0";		
		}
		riskp.compositeDelta = getValue(line, 102, 103)  +  getValue(line, 97, 98) + "." +  getValue(line, 98, 102);
		riskp.iv = getValue(line, 103, 105) + "." + getValue(line, 105, 111);
		riskp.settelementPrice = getValue(line, 118, 119) + getValue(line, 111, 116) +"." +getValue(line, 116, 118);

	}
	
	public String getProductType(String input) {
		if ("PHY".equals(input.trim())) return "OOP";
		if ("OOF".equals(input.trim())) return "OOP";
		
		return input;
		
		
	}

	/**
	 * All the "Input start with 1";
	 */
	public String getValue(String input, int start, int end) {
		if (input != null && input.length() >= end-1) {
			// hack to start with 1 instead of 0
			return input.substring(start - 1, end - 1);
		}

		return null;
	}

}
