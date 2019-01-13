package com.kometsales.flowers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	public static final String NUMERIC_PATTERN = "^[\\d]+$";
	public static final String STRING_PATTERN = "^[A-Za-z\\d]+$";
	public static final String DATE_PATTERN = "^\\d{2}/\\d{2}/\\d{4}$";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	
	public static boolean validateRegexInData(String data, Pattern pattern) {
		Matcher matcher = pattern.matcher(data.toUpperCase());
		return matcher.find();
	}
}
