package com.ambiverse.api;

import java.io.IOException;

import com.ambiverse.api.model.ErrorMessage;


/**
 * Signals that an {@link IOException} for the Ambiverse API has occurred.
 */
public class ApiException extends IOException {
	private static final long serialVersionUID = 2822786972450905813L;

	/**
	 * Constructor for a new instance of {@link ApiException}.
	 * 
	 * @param message JSON-wrapped error message of type {@link com.ambiverse.api.model.ErrorMessage}
	 */
	public ApiException(ErrorMessage message) {
		super(message.getMessage());
	}
	
	/**
	 * Constructor for a new instance of {@link ApiException}.
	 * 
	 * @param message Plain-text error message
	 */
	public ApiException(String message) {
		super(message);
	}
	
}
