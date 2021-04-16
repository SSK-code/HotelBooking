package com.booking.exception;

// This class will be used to throw exception if there is any invalid data which
// can not be processed
// e.g. null or empty parameters
// This will throw 422 - Unprocessable Enitty
public class InputDataValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputDataValidationException(String message) {
		super(message);
	}

}
