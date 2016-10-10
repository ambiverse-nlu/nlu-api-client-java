package com.ambiverse.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;


/**
 * {@link HttpRequestInitializer} for the API client that carries authentication credentials. The
 * original implementation of {@link com.google.api.client.auth.oauth2.Credential} by Google does
 * not conform to <a href="http://www.ietf.org/rfc/rfc4627.txt">RFC 4627</a> as it does not set the
 * default encoding of the "application/json" media type to UTF-8. The default encoding is set
 * properly in this request initializer.
 * 
 * @see <a href="https://github.com/google/google-http-java-client/issues/221">Discussion on GitHub</a>
 */
public class Credential extends com.google.api.client.auth.oauth2.Credential {
	
	/**
	 * Constructor for a new instance of {@link Credential}.
	 * 
	 * @param method The {@link AccessMethod} to use.
	 */
	public Credential(AccessMethod method) {
		super(method);
	}
	
	/**
	 * Initializes this {@link com.google.api.client.auth.oauth2.Credential} subclass. Adds an
	 * additional interceptor that properly sets the default encoding of the "application/json"
	 * media type to UTF-8 as required by <a href="http://www.ietf.org/rfc/rfc4627.txt">RFC 4627</a>.
	 * 
	 * @param request The {@link HttpRequest} to be initialized.
	 */
	@Override
	public void initialize(HttpRequest request) throws IOException {
		// Initially, pass the initialization on to super.
		super.initialize(request);
		
		// Set the timeouts.
		request.setConnectTimeout(3 * 60000);  // 3 minutes connect timeout
		request.setReadTimeout(3 * 60000);  // 3 minutes read timeout
		
		// Add an additional interceptor.
		request.setResponseInterceptor(new HttpResponseInterceptor() {
			@Override
			public void interceptResponse(HttpResponse response) throws IOException {
				// Decode JSON as UTF-8 if no charset is specified.
				if (response.getContentType().equals("application/json") &&
					response.getMediaType() != null &&
					response.getMediaType().getCharsetParameter() == null)
				{
					response.getMediaType().setCharsetParameter(StandardCharsets.UTF_8);
				}
			}
		});
		
		// Add a custom handler for unsuccessful responses.
		request.setUnsuccessfulResponseHandler(
				new UnsuccessfulResponseHandler());
	}
	
}
