package com.ambiverse.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;


/**
 * Custom HTTP client request base class for the {@link AmbiverseApiClient}. For proper interaction
 * with the Ambiverse API, we disable GZip content encoding by default. 
 *
 * @param <T> Model class that the JSON response object will be deserialized to.
 */
public class Request<T> extends AbstractGoogleClientRequest<T> {
	
	/**
	 * Constructor for a new instance of {@link Request}.
	 * 
	 * @param abstractGoogleClient Instance of {@link AmbiverseApiClient}
	 * @param requestMethod The HTTP request method to use, e.g. "GET" or "POST".
	 * @param uriTemplate The endpoint URL
	 * @param httpContent The payload to send to the endpoint.
	 * @param responseClass The model class to deserialize the JSON response to.
	 */
	protected Request(AbstractGoogleClient abstractGoogleClient, String requestMethod, String uriTemplate,
			HttpContent httpContent, Class<T> responseClass) {
		super(abstractGoogleClient, requestMethod, uriTemplate, httpContent, responseClass);
		
//		this.setDisableGZipContent(true);		
	}
	
	/**
	 * Convenience debug method for reading the request payload as string.
	 * 
	 * @return Request payload as string
	 * @throws IOException
	 */
	protected String buildHttpRequestAndGetPayload() throws IOException {
		HttpRequest request = this.buildHttpRequest();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		request.getContent().writeTo(bos);
		
		return new String(bos.toByteArray());
	}
}
