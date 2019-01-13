package com.kometsales.flowers.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kometsales.flowers.exception.ServiceException;

public class Utils {

	
	public static Date convertStringToDate(String stringDate, String format) throws ServiceException {
		Date date = null;
		if (stringDate != null && !"".equals(stringDate)) {
			try {
				date = new SimpleDateFormat(format).parse(stringDate);
			} catch (ParseException e) {
				throw new ServiceException(ServiceException.DATE_FORMAT_ERROR,e);
			}
		}

		return date;
	}
}
