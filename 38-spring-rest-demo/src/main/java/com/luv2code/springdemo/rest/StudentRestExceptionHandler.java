package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

	// add exception handling code here
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

		// create a StudentErrorResponse
		StudentErrorResponse studentErrorResponse = new StudentErrorResponse();

		studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		studentErrorResponse.setMessage(exc.getMessage());
		studentErrorResponse.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
	}

	// add another exception handler ... to catch any exception (catch all)

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

		// create a StudentErrorResponse
		StudentErrorResponse studentErrorResponse = new StudentErrorResponse();

		studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		studentErrorResponse.setMessage(exc.getMessage());
		studentErrorResponse.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);

	}
	
}
