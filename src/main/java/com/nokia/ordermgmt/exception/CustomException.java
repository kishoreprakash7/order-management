package com.nokia.ordermgmt.exception;

/**
 * @author Kishore Prakash
 *
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param developerMessage
	 */
	public CustomException(String developerMessage) {
		super(developerMessage);
	}

}
