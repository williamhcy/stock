package com.qaoption.util;

import java.math.BigDecimal;

import com.qaoption.model.OptionContract;
import com.qaoption.model.Portfolio;

public class PortfolioUtil {
	public static void shiftStrike(Portfolio port, BigDecimal shift) {
		for (OptionContract c : port.getOptions()) {
			c.setStrike(c.getStrike().add(shift));
		}
		
	}
}
