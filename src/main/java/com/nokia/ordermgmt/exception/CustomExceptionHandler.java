package com.nokia.ordermgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Kishore Prakash
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param ex
	 * @return Response Entity
	 */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleException(CustomException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
