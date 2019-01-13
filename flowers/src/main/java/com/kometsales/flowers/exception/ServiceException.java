package com.kometsales.flowers.exception;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String BD_ERROR = "Error in access to the database";
	public static final String CUSTOMER_ALREADY_EXIST = "The customer already exist in the database";
	public static final String DATE_FORMAT_ERROR = "The date is wrong";
	public static final String READ_FILE_ERROR = "Error mapping the file in object";
	public static final String SLEEP_ERROR = "Error sleeping the thread";
	public static final String INPUT_STREAM_ERROR = "Error extracting the inputStream";
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable throwable) {
		super(message,throwable);
	}
	
}
