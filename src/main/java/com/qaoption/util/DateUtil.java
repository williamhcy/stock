package com.qaoption.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date getDateWithoutTime() 
	  throws ParseException {
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    return formatter.parse(formatter.format(new Date()));
	}

}
