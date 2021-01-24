package com.luv2code.springdemo.rest;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String arg0) {
		super(arg0);
	}
	
	public CustomerNotFoundException(Throwable arg0) {
		super(arg0);
	}
	
	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	
}
