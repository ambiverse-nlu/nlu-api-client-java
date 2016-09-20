package com.ambiverse.api.model;


import com.google.api.client.util.Key;

public class ErrorMessage {

	@Key
	private String message;

	public String getMessage() {
		return message;
	}

}
