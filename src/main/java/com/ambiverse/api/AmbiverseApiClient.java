package com.ambiverse.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.ambiverse.api.model.AnalyzeInput;
import com.ambiverse.api.model.AnalyzeOutput;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientCredentialsTokenRequest;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;


/**
 * Ambiverse API Client that builds on the
 * <a href="https://developers.google.com/api-client-library/java/">Google API Client Library for Java</a>.
 * The client abstracts API requests to native Java objects and methods. That way, it allows you to
 * easily analyze texts with the {@link #entityLinking() Entity Linking Service} or query the
 * {@link #knowledgeGraph() Knowledge Graph Service} for details about entities.
 * 
 * @see <a href="https://developer.ambiverse.com/docs">API Documentation</a>
 * @see <a href="https://developer.ambiverse.com/overview">API Overview</a>
 */
public class AmbiverseApiClient extends AbstractGoogleJsonClient {

	/** The host URL of the Ambiverse API */
	public static final String HOST_URL = "https://api.ambiverse.com";

	/** The service path of the current schema */
	public static final String SERVICE_PATH = "/v2";

	/** The file that the client credentials are read from */
	private static final String CLIENT_SECRETS_FILENAME = "client_secrets.json";
	
	private static final Logger logger = Logger.getLogger(AmbiverseApiClient.class.getName());

	private EntityLinking entityLinking = null;
	private KnowledgeGraph knowledgeGraph = null;

	
	/**
	 * Constructor for a new instance of {@link AmbiverseApiClient}.
	 * 
	 * @param builder The builder to use for constructing the client instance.
	 */
	protected AmbiverseApiClient(Builder builder) {
		super(builder);
	}

	/**
	 * Constructor for a new instance of {@link AmbiverseApiClient}.
	 * 
	 * @param transport The HTTP Transport to use.
	 * @param jsonFactory The JSON Factory to use.
	 * @param httpRequestInitializer The HTTP Request Initializer to use, e.g. a {@link Credential}.
	 */
	public AmbiverseApiClient(HttpTransport transport, JsonFactory jsonFactory,
			HttpRequestInitializer httpRequestInitializer) {
		this(new Builder(transport, jsonFactory, httpRequestInitializer)
				.setApplicationName("SampleCompany-SampleApp/1.0"));
	}
	
	/**
	 * Authenticates your client application against the Ambiverse API endpoint via the OAuth 2
	 * protocol. Your client credentials are read from client_secrets.json on your classpath and
	 * exchanged for an API access token, which is stored within the API client throughout your
	 * session.
	 * @see <a href="https://developers.google.com/api-client-library/python/guide/aaa_client_secrets">Google Client Secrets file format</a>
	 * @return Credential object holding your API access token
	 * @throws IOException
	 */
	public static Credential authorize(HttpTransport transport, JsonFactory jsonFactory)
			throws IOException {
		// Read the credentials from the provided client_secrets.json file
		GoogleClientSecrets clientSecrets = null;
		try {
			clientSecrets = GoogleClientSecrets.load(jsonFactory,
				new InputStreamReader(AmbiverseApiClient.class.getClassLoader().getResourceAsStream(CLIENT_SECRETS_FILENAME)));
		} catch (NullPointerException e) {
			logger.severe("Copy src/main/resources/client_secrets_template.json to src/main/resources" + CLIENT_SECRETS_FILENAME + " , " +
										System.lineSeparator() +
										"and make sure to specify your client credentials there.");
			System.exit(1);
		}

		// Request an access token
		TokenResponse response = new ClientCredentialsTokenRequest(
				transport,
				jsonFactory,
				new GenericUrl(clientSecrets.getWeb().getTokenUri()))
						.setClientAuthentication(new ClientParametersAuthentication(
								clientSecrets.getWeb().getClientId(),
								clientSecrets.getWeb().getClientSecret()))
						.execute();
		
		// Return the Credential object
		Credential c = new Credential(BearerToken.authorizationHeaderAccessMethod());
		c.setAccessToken(response.getAccessToken());
		
		return c;
	}

	/**
	 * Builder used for initializing the {@link AmbiverseApiClient}.
	 *
	 */
	public static final class Builder
			extends AbstractGoogleJsonClient.Builder {
		
		/**
		 * Constructor for a new {@link Builder} instance.
		 * 
		 * @param transport The HTTP Transport to use.
		 * @param jsonFactory The JSON Factory to use.
		 * @param httpRequestInitializer The HTTP Request Initializer to use, e.g. a {@link Credential}.
		 */
		public Builder(HttpTransport transport, JsonFactory jsonFactory,
				HttpRequestInitializer httpRequestInitializer) {
			super(transport,
				  jsonFactory,
				  HOST_URL,
				  SERVICE_PATH,
				  httpRequestInitializer,
				  false);
		}

		/**
		 * Builds a new instance of {@link AmbiverseApiClient}.
		 */
		@Override
		public AmbiverseApiClient build() {
			return new AmbiverseApiClient(this);
		}
		
		/**
		 * Sets the application name to be used in the UserAgent header of each request or null
		 * for none.
		 * 
		 * @param applicationName The name of the application
		 */
		@Override
		public Builder setApplicationName(
				String applicationName) {
			 super.setApplicationName(applicationName);
			 return this;
		}
		
		
	}
	
	/**
	 * Access method for the Entity Linking Service.
	 * 
	 * @see <a href="https://developer.ambiverse.com/overview#entity-linking-service">Entity Linking Service Overview</a>
	 * @return Instance of {@link EntityLinking}
	 */
	public synchronized EntityLinking entityLinking() {
		if (entityLinking == null) {
			entityLinking = new EntityLinking();
	    }
	    return entityLinking;
	}
	
	/**
	 * Wrapper for the REST endpoints of the Entity Linking Service.
	 */
	public class EntityLinking {
		public static final String PATH = "entitylinking";
		
		private Analyze analyze = null;
		
		/**
		 * Access method for the {@link Analyze} resource.
		 * 
		 * @return Instance of {@link Analyze}
		 */
		public Analyze analyze() {
			if (analyze == null) {
				analyze = new Analyze();
			}
			return analyze;
		}
		
		/**
		 * Wrapper for the REST endpoints of the Analyze resource.
		 */
		public class Analyze {
			private static final String PATH = EntityLinking.PATH + "/analyze";
			
			/**
			 * Access method for the text analysis endpoint.
			 * 
			 * @param input Instance of {@link AnalyzeInput}
			 * @return HTTP request instance of {@link Post} for {@link AnalyzeOutput}.
			 */
			public Post process(AnalyzeInput input) {
				return new Post(input);
			}
			
			/**
			 * HTTP request for {@link AnalyzeOutput}.			 *
			 */
			public class Post extends Request<com.ambiverse.api.model.AnalyzeOutput> {				
				protected Post(AnalyzeInput input) {		
					super(AmbiverseApiClient.this,
						  "POST",
						  PATH,
						  new JsonHttpContent(AmbiverseApiClient.this.getJsonFactory(), input),
						  com.ambiverse.api.model.AnalyzeOutput.class);
				}
			}
			
			/**
			 * Access method for the metadata endpoint.
			 * 
			 * @return HTTP request instance of {@link GetMeta} for {@link com.ambiverse.api.model.Meta}.
			 */
			public GetMeta getMeta() {
				return new GetMeta();
			}
			
			/**
			 * HTTP request for {@link com.ambiverse.api.model.Meta}.
			 */
			public class GetMeta extends Request<com.ambiverse.api.model.Meta> {				
				private static final String PATH = Analyze.PATH + "/_meta";
				
				protected GetMeta() {				
					super(AmbiverseApiClient.this, "GET", PATH, null, com.ambiverse.api.model.Meta.class);
				}				
			}
		}
	}
	
	/**
	 * Access method for the Knowledge Graph Service.
	 * 
	 * @see <a href="https://developer.ambiverse.com/overview#knowledge-graph-service">Knowledge Graph Service Overview</a>
	 * @return Instance of {@link KnowledgeGraph}
	 */
	public synchronized KnowledgeGraph knowledgeGraph() {
		if (knowledgeGraph == null) {
			knowledgeGraph = new KnowledgeGraph();
	    }
	    return knowledgeGraph;
	}
	
	/**
	 * Wrapper for the REST endpoints of the Knowledge Graph Service.
	 */
	public class KnowledgeGraph {		
		public static final String PATH = "knowledgegraph";
		
		private Entities entities = null;
		private Categories categories = null;
		
		/**
		 * Access method for the {@link Entities} resource.
		 * 
		 * @return Instance of {@link Entities}
		 */
		public synchronized Entities entities() {
			if (entities == null) {
				entities = new Entities();
			}
			return entities;
		}
		
		/**
		 * Access method for the {@link Categories} resource.
		 * 
		 * @return Instance of {@link Categories}
		 */
		public synchronized Categories categories() {
			if (categories == null) {
				categories = new Categories();
			}
			return categories;
		}
		
		/**
		 * Wrapper for the REST endpoints of the Entities resource.
		 */
		public class Entities {			
			private static final String PATH = KnowledgeGraph.PATH + "/entities";
			
			/**
			 * Access method for the {@link com.ambiverse.api.model.Entities} resource
			 * 
			 * @return HTTP request instance of {@link Post} for {@link com.ambiverse.api.model.Entities}.
			 */
			public Post get(String... yagoIDs) {
				return new Post(yagoIDs);
			}
			
			/**
			 * HTTP request for {@link com.ambiverse.api.model.Entities}.
			 */
			public class Post extends Request<com.ambiverse.api.model.Entities> {
				private static final String PATH = Entities.PATH;
				
				protected Post(String... yagoIDs) {				
					super(AmbiverseApiClient.this,
						  "POST",
						  PATH,
						  new JsonHttpContent(AmbiverseApiClient.this.getJsonFactory(), yagoIDs),
						  com.ambiverse.api.model.Entities.class);
				}	
			}
			
			/**
			 * Access method for the metadata resource
			 * 
			 * @return HTTP request instance of {@link GetMeta} for {@link com.ambiverse.api.model.Meta}.
			 */
			public GetMeta getMeta() {
				return new GetMeta();
			}
			
			/**
			 * HTTP request for {@link com.ambiverse.api.model.Meta}.
			 */
			public class GetMeta extends Request<com.ambiverse.api.model.Meta> {				
				private static final String PATH = Entities.PATH + "/_meta";
				
				protected GetMeta() {				
					super(AmbiverseApiClient.this, "GET", PATH, null, com.ambiverse.api.model.Meta.class);
				}				
			}
		}
		
		/**
		 * Wrapper for the REST endpoints of the Categories resource.
		 */
		public class Categories {
			private static final String PATH = KnowledgeGraph.PATH + "/categories";
					
			/**
			 * Access method for the {@link com.ambiverse.api.model.Categories} resource
			 * 
			 * @return HTTP request instance of {@link Post} for {@link com.ambiverse.api.model.Categories}.
			 */
			public Post get(String... yagoIDs) {
				return new Post(yagoIDs);
			}
			
			/**
			 * HTTP request for {@link com.ambiverse.api.model.Categories}.
			 */
			public class Post extends Request<com.ambiverse.api.model.Categories> {				
//				private static final String PATH = Categories.PATH + "/{yagoIDs}";
//				
//				@Key
//				private String[] yagoIDs;
				
				protected Post(String... yagoIDs) {				
					super(AmbiverseApiClient.this,
							"POST",
							PATH, 
							new JsonHttpContent(AmbiverseApiClient.this.getJsonFactory(), yagoIDs), 
							com.ambiverse.api.model.Categories.class);
//					this.yagoIDs = yagoIDs;
				}				
			}
			
			/**
			 * Access method for the metadata resource
			 * 
			 * @return HTTP request instance of {@link GetMeta} for {@link com.ambiverse.api.model.Meta}.
			 */
			public GetMeta getMeta() {
				return new GetMeta();
			}
			
			/**
			 * HTTP request for {@link com.ambiverse.api.model.Meta}.
			 */
			public class GetMeta extends Request<com.ambiverse.api.model.Meta> {				
				private static final String PATH = Categories.PATH + "/_meta";
				
				protected GetMeta() {				
					super(AmbiverseApiClient.this, "GET", PATH, null, com.ambiverse.api.model.Meta.class);
				}				
			}
		}
	}
}
