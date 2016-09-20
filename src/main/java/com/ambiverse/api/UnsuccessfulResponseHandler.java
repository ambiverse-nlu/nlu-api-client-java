package com.ambiverse.api;

import java.io.IOException;
import java.util.logging.Logger;

import com.ambiverse.api.model.ErrorMessage;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.json.gson.GsonFactory;

/**
 * Handler for unsuccessful HTTP responses. If present, it parses a JSON-serialized object of
 * {@link ErrorMessage} and reads the error messages. In case of other errors, it returns the
 * error message as a string.
 */
public class UnsuccessfulResponseHandler implements HttpUnsuccessfulResponseHandler {

	private static final Logger logger = Logger.getLogger(UnsuccessfulResponseHandler.class.getName());
	
	/** The default JSON Factory to use. */
	private GsonFactory jsonFactory = new GsonFactory();

	
	/**
	 * Handler that will be invoked when an error response is received.
	 * 
	 * @param request Instance of {@link HttpRequest}
	 * @param response Instance of {@link HttpResponse}
	 * @param supportsRetry Flag that indicates whether there will be a retry if this handler
	 * returns true. 
	 */
	@Override
	public boolean handleResponse(HttpRequest request, HttpResponse response, boolean supportsRetry)
			throws IOException {	
		
		IOException exception;
		
		try {
			String responseString = response.parseAsString();
			
			ErrorMessage msg = jsonFactory.fromString(responseString, ErrorMessage.class);
			
			exception = new ApiException(msg);
		} catch (IOException e) {
			String errorMessage = "Couldn't parse JSON error message: " + e.getMessage();
			
			logger.severe(errorMessage);
			
			exception = new ApiException(errorMessage);
		}
		
		throw exception;
	}

}
