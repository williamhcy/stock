package com.qaoption.util;

import java.util.Date;

import com.qaoption.model.FutureContract;
import com.qaoption.model.OptionContract;

public class BSCalc {
	
	public static double futurePriceByContract(OptionContract future, double underlyingPrice, Date currentDate) {
		return (underlyingPrice - future.getStrike().doubleValue());
	}
	
	
	public static double optionPriceByContract(OptionContract option, double underlyingPrice, Date currentDate) {
		double diffInDaysPeryear = (int)( (option.getMatDate().getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24) );
		System.out.println(diffInDaysPeryear);
		return optionPrice(option.getCallPut(), underlyingPrice, option.getStrike().doubleValue(), diffInDaysPeryear/365, option.getInterest().doubleValue(), option.getIv());
	}

	public static double optionPrice(String CallPutFlag, double S, double X, double T,
			double r, double v) {
		double d1, d2;

		d1 = (Math.log(S / X) + (r + v * v / 2) * T) / (v * Math.sqrt(T));
		d2 = d1 - v * Math.sqrt(T);

		if (CallPutFlag.equalsIgnoreCase("C")) {
			return S * CND(d1) - X * Math.exp(-r * T) * CND(d2);
		} else {
			return X * Math.exp(-r * T) * CND(-d2) - S * CND(-d1);
		}
	}

	// The cumulative normal distribution function
	protected static double CND(double X) {
		double L, K, w;
		double a1 = 0.31938153, a2 = -0.356563782, a3 = 1.781477937, a4 = -1.821255978, a5 = 1.330274429;

		L = Math.abs(X);
		K = 1.0 / (1.0 + 0.2316419 * L);
		w = 1.0
				- 1.0
				/ Math.sqrt(2.0 * Math.PI)
				* Math.exp(-L * L / 2)
				* (a1 * K + a2 * K * K + a3 * Math.pow(K, 3) + a4
						* Math.pow(K, 4) + a5 * Math.pow(K, 5));

		if (X < 0.0) {
			w = 1.0 - w;
		}
		return w;
	}

}
