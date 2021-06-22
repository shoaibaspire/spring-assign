package com.daofab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoRecordFoundException(ResourceNotFoundException ex) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("No Record Found");
		return errorResponse;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleDefaultException(Exception ex) {
	    ErrorResponse response = new ErrorResponse();
	    response.setMessage(ex.getMessage());
	    return response;
	}
	
}