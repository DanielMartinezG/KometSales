package com.kometsales.flowers.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorHandled implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	private String errorDescription;
	
	ErrorHandled(HttpStatus status) {
		this.status = status;
	}
	
	ErrorHandled(HttpStatus status, Throwable ex){
		this.status = status;
		this.errorDescription = ex.getLocalizedMessage();
	}
	
	ErrorHandled(HttpStatus status,String message, Throwable e){
		this.status = status;
		this.message = message;
		this.errorDescription = e.getLocalizedMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
