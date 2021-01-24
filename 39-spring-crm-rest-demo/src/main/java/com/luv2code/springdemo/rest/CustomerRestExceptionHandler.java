package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	// Add an exception handler for CustomerNotFoundException
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(CustomerNotFoundException exception){
		
		// Create CustomerErrorResponse
		
		CustomErrorResponse error=new 
				CustomErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	// Add another exception handler ... to catch any exception (catch all)
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(Exception exception){
		
		// Create CustomerErrorResponse
		
		CustomErrorResponse error=new 
				CustomErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
}
