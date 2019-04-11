package com.qaoption.loading;

public class LoadingOptionStrikeSpread {
	/*
	insert into strike_spread (stockCode, strikeSpread)
	SELECT distinct contractStockCode, optionStrike FROM `risk` WHERE contractType = 'OOP' and compositeDelta < 0.8 and compositeDelta > -0.80  and optionContractMonth = DATE_FORMAT(DATE_ADD(Now(), INTERVAL 7 DAY), '%Y%m') order by contractStockCode, optionStrike
	*/
}
