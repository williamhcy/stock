package com.qaoption.gen;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.qaoption.model.OptionContract;
import com.qaoption.model.OptionResult;
import com.qaoption.model.Portfolio;
import com.qaoption.service.RiskService;
import com.qaoption.util.BSCalc;
import com.qaoption.util.DateUtil;

public class GenPL {
	final static Logger logger = LogManager.getRootLogger();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			// testing the generation
			Portfolio portfolio = new Portfolio();
			String sc = "HSI";
			portfolio.setStockCode(sc);
			DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
			
			OptionContract c1 = new OptionContract();
			c1.setCallPut("C");
			c1.setStockCode(sc);
			c1.setContractType("OOP");
			c1.setStrike(new BigDecimal(29800));
			c1.setMatDate(format.parse("20190328"));
			c1.setInterest(new BigDecimal(0.02));
			c1.setPremium(new BigDecimal(100));
			c1.setQty(1);
			c1.setIv(0.20);
			
			
			
			OptionContract c2 = new OptionContract();
			c2.setCallPut("P");
			c2.setStockCode(sc);
			c2.setContractType("OOP");
			c2.setStrike(new BigDecimal(29600));
			c2.setMatDate(format.parse("20190328"));
			c2.setInterest(new BigDecimal(0.02));
			c2.setPremium(new BigDecimal(200));
			c2.setQty(-1);
			c2.setIv(0.20);
			
			
			
			OptionContract c3 = new OptionContract();
			
			OptionContract f1 = new OptionContract();
			f1.setStockCode(sc);
			f1.setMatDate(format.parse("20190328"));
			f1.setContractType("FUT");
			f1.setQty(1);
			f1.setStrike(new BigDecimal(30000));
			
			portfolio.addOption(c1);
			portfolio.addOption(c2);
			//portfolio.addOption(c3);
			
			//portfolio.addFuture(f1);
			
			// get Portfilio from DB
			Map<String, Portfolio> portfolioMap = RiskService.getPortfolioByUserId(1);
			Portfolio port = null;
			for (Map.Entry<String, Portfolio> entry : portfolioMap.entrySet()) {
			    String key = entry.getKey();
			    port = entry.getValue();
			    logger.debug("portfolio:" + port.getStockCode());
			}			
			
			GenPL pl = new GenPL();
			OptionResult[][] arr = pl.genPortfolioPL(port, new BigDecimal(28600), new BigDecimal(200), 25);

			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(i+""+j+"PL:"+ arr[i][j].getProfitLoss() + " ");
				}
				System.out.println("");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}
	
	
	/**
	 * loop for +/- 5 interval and [input days] day
	 * @param portfolio
	 * @param priceInterval
	 * @param days
	 * @return
	 */
	public OptionResult[][] genPortfolioPL(Portfolio portfolio, BigDecimal price, BigDecimal priceInterval, int days) throws Exception {
		OptionResult[][] optionResultArray = new OptionResult[days][11];
		Date today = DateUtil.getDateWithoutTime();
		// loop for days
		for (int y=0; y < days; y++) {
			// loop for price interval
			for (int x=-5; x < 6; x++) {
				OptionResult result = new OptionResult();
				for (OptionContract option: portfolio.getOptions()) {
					
					long todayLong = today.getTime();
					Date inputDate = new Date(todayLong + y*1000 * 60 * 60 * 24);
					double optionPrice = BSCalc.optionPriceByContract(option, (x * priceInterval.doubleValue() + price.doubleValue()), inputDate);
					// calc profit and loss
					System.out.println("optionPrice : " + optionPrice + inputDate + " " + (x * priceInterval.doubleValue() + price.doubleValue()));
					double pl = (optionPrice - option.getPremium().doubleValue()) * option.getQty();
					result.setProfitLoss(result.getProfitLoss() + pl);
					
					// find margin...
				}
				
				for (OptionContract future: portfolio.getFutures()) {
					double futurePrice = BSCalc.futurePriceByContract(future, (x * priceInterval.doubleValue() + price.doubleValue()), null);
					// calc profit and loss
					System.out.println("futurePrice : " + futurePrice + " " + (x * priceInterval.doubleValue() + price.doubleValue()));
					double pl = (futurePrice) * future.getQty();
					result.setProfitLoss(result.getProfitLoss() + pl);
					
					// find margin...
				}
				
				optionResultArray[y][x+5] = result;
				
			}
		}
		
		return optionResultArray;
	}

}
