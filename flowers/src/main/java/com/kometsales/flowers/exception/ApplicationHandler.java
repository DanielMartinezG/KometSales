package com.kometsales.flowers.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler{
	public static final String JSON_FORMAT_ERROR = "Error in JSON format";
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildResponseEntity(new ErrorHandled(HttpStatus.BAD_REQUEST, JSON_FORMAT_ERROR, ex));
	}
	
	@ExceptionHandler({ ServiceException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> serviceException(Exception ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return buildResponseEntity(new ErrorHandled(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
	}
	
	private ResponseEntity<Object> buildResponseEntity(ErrorHandled errorHandler) {
		return new ResponseEntity<>(errorHandler, errorHandler.getStatus());
	}
}
