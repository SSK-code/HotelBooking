package com.booking.exception;

import java.time.Instant;

import javax.validation.ConstraintViolationException;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponseMessage handleResourceNotFoundException(RuntimeException ex) {
		return sendResponse(HttpStatus.NOT_FOUND, ex);
	}

	@ExceptionHandler({ InputDataValidationException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ExceptionResponseMessage handleInvalidInputDataException(RuntimeException ex) {
		return sendResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponseMessage handleBadRequest(RuntimeException ex) {
		return sendResponse(HttpStatus.BAD_REQUEST, ex);
	}

	private ExceptionResponseMessage sendResponse(HttpStatus status, RuntimeException ex) {

		return new ExceptionResponseMessage(Instant.now(), status.value(), ex.getClass().toString(), ex.getMessage());
	}
}
