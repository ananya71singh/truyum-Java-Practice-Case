package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date convertToDate(String date)
	{
		Date fDate=null;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		try {
			fDate= simpleDateFormat.parse(date);
		} catch (ParseException e) {
			
			System.out.println("Date Fromatting error");
		}
		return fDate;
	}

}
