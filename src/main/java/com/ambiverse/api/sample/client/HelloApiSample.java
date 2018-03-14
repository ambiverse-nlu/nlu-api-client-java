package com.ambiverse.api.sample.client;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import com.ambiverse.api.AmbiverseApiClient;
import com.ambiverse.api.ApiException;
import com.ambiverse.api.model.*;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;


/**
 * This is a basic sample for the
 * <a href="https://www.ambiverse.com/natural-language-understanding-api/">Ambiverse Natural Language Understanding API</a>.
 * The API extracts entities from unstructured text, enabling a more precise transformation of texts
 * into actionable, measurable, and easily accessible knowledge. 
 * 
 * We will guide you through the two services currently exposed by the API:
 * 
 * 1. The Entity Linking service which identifies and links names of persons, locations,
 * organizations, or products, to the Wikipedia-derived YAGO knowledge graph.
 * 
 * 2. The Knowledge Graph service which allows you to obtain further information about these
 * entities, such as Wikipedia links, textual descriptions, images, and lists of relevant
 * categories.
 * 
 * A complete walk-through can be found in the <a href="https://developer.ambiverse.com/overview">
 * Natural Language Understanding API Overview</a>.
 *  
 * Once you added your OAuth 2 client credentials to the client_secrets.json file, you can just run
 * this class and inspect the output of the API. We access the Ambiverse Natural Language 
 * Understanding API using our SDK which builds on the
 * <a href="https://developers.google.com/api-client-library/java/">Google API Client Library for Java</a>.
 */
public class HelloApiSample {
	private static final String LF = System.lineSeparator();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport = new NetHttpTransport();
	
	private static final Logger logger = Logger.getLogger(HelloApiSample.class.getName());
	
	private static final NumberFormat numberFormat = NumberFormat.getIntegerInstance();
	
	private AmbiverseApiClient client = null;
	

	/** Main method for running some sample request against the Ambiverse API endpoint. */
	public static void main(String[] args) throws IOException {
		HelloApiSample helloApi = new HelloApiSample();
		
		// Initialize the API Client
		helloApi.initialize();
		
		// Run some sample requests against the Entity Linking Service.
		helloApi.runEntityLinkingSamples();		
		
		// Run some sample request against the Knowledge Graph Service.
		helloApi.runKnowledgeGraphSamples();
		
		// Show how to handle errors
		helloApi.runErrorHandlingSamples();
	}
	
	/**
	 * Runs some sample requests against the {@link AmbiverseApiClient#entityLinking() Entity 
	 * Linking Service}.
	 * 
	 * @throws IOException
	 */
	private void runEntityLinkingSamples() throws IOException {
		System.out.println("*** Running Entity Linking Service samples ***" + LF);
		
		// Entity Linking Service - Analyze a document
		String text = "Ma founded Alibaba in Hangzhou with investments from SoftBank and Goldman.";
		
		AnalyzeInput input = new AnalyzeInput()
				.withLanguage("en")		// Optional. If not set, language detection happens automatically.
				.withText(text);
		System.out.println("Sent text to /entitylinking/analyze for analysis: \"" + text + "\"");
		
		AnalyzeOutput output = client.entityLinking().analyze().process(input).execute();
		
		System.out.println("Recognized entities:");
		
		for (Match match : output.getMatches()) {
			System.out.println("- Text match: " + match.getText() +
							   " (" + match.getCharOffset() +
							   ", " +
							   (match.getCharOffset() + match.getCharLength()) +
							   ") ");
			System.out.println("  Linked entity: " + match.getEntity().getId() +
							   " (confidence score: " + match.getEntity().getConfidence() + ")");
		}

		System.out.println("All unique entities:");

		for (OutputEntity entity : output.getEntities()) {
			System.out.println("  ID:"+entity.getId()+" ["+entity.getType()+"] (salience score: "+entity.getSalience()+")");
		}

		
		System.out.println(LF);
		
		
		// Entity Linking Service - Request metadata from the Entity Linking endpoint
		System.out.println("Querying metadata at the /entitylinking/analyze/_meta endpoint.");
		
		Meta m = client.entityLinking().analyze().getMeta().execute();
		System.out.println("- dump version: " + m.getDumpVersion());
		System.out.println("- supported languages: " + m.getLanguages());
		System.out.println("- database creation date: " + m.getCreationDate());
		
		System.out.println(LF);
	}
	
	/**
	 * Runs some sample requests against the {@link AmbiverseApiClient#knowledgeGraph() Knowledge
	 * Graph Service}.
	 * 
	 * @throws IOException
	 */
	private void runKnowledgeGraphSamples() throws IOException {
		System.out.println("*** Running Knowledge Graph Service samples ***" + LF);
		
		// Knowledge Graph Service - Request a single entity
		String id = "http://www.wikidata.org/entity/Q193326";	// This entity is "Goldman Sachs".
		
		System.out.println("Querying the Knowledge Graph at /knowledgegraph/entities for the entity \"" + id + "\".");
		
		Entities entities = client.knowledgeGraph().entities()
				.get(id)
				.execute();
		
		for (Map.Entry<String, Entity> entityEntry : entities.getEntities().entrySet()) {

			Entity e = entityEntry.getValue();
			System.out.println("- Names: ");
			for(Map.Entry<String, Label> entry : e.getNames().entrySet()) {
				System.out.println("  - "+entry.getValue().getLanguage()+": "+entry.getValue().getValue());
			}

			System.out.println("- Short descriptions: ");
			for(Map.Entry<String, Label> entry : e.getDescriptions().entrySet()) {
				System.out.println("  - "+entry.getValue().getLanguage()+": "+entry.getValue().getValue());
			}

			System.out.println("- Detailed descriptions: ");
			for(Map.Entry<String, Label> entry : e.getDetailedDescriptions().entrySet()) {
				System.out.println("  - "+entry.getValue().getLanguage()+": "+entry.getValue().getValue());
			}

			System.out.println("- Categories (subset of 10): " + new ArrayList<String>(e.getCategories()).subList(0, 10));
			System.out.println("- Links: ");
			for(Map.Entry<String, Label> entry : e.getLinks().entrySet()) {
				System.out.println("  - "+entry.getValue().getLanguage()+": "+entry.getValue().getValue());
			}

			System.out.println("- Image: " + e.getImage());
		}
		
		System.out.println(LF);
		
		
		// Knowledge Graph Service - Request multiple entities
		System.out.println("Querying the knowledge graph at /knowledgegraph/entities for multiple entities.");		
		
		entities = client.knowledgeGraph().entities()
				.get("http://www.wikidata.org/entity/Q1137062",			// Jack Ma
					 "http://www.wikidata.org/entity/Q1359568",			// Alibaba Group
					 "http://www.wikidata.org/entity/Q4970")			// Hangzhou
				.execute();
		
		for (Map.Entry<String, Entity> entry : entities.getEntities().entrySet()) {
			System.out.println("- Entity: " + entry.getValue().getNames().get("en").getValue() + " (" + entry.getValue().getId() + ")");
		}
		
		System.out.println(LF);
		
		
		// Knowledge Graph Service - Request metadata from the Entities endpoint
		System.out.println("Querying metadata at the /knowledgegraph/entities/_meta endpoint.");
		
		Meta m = client.knowledgeGraph().entities().getMeta().execute();
		System.out.println("- dump version: " + m.getDumpVersion());
		System.out.println("- supported languages: " + m.getLanguages());
		System.out.println("- database creation date: " + m.getCreationDate());
		System.out.println("- collection size: " + numberFormat.format(m.getCollectionSize()));
		
		System.out.println(LF);
		
				
		// Knowledge Graph Service - Request multiple categories
		System.out.println("Querying the knowledge graph at /knowledgegraph/categories for multiple categories.");
		
		Categories categories = client.knowledgeGraph().categories()
				.get("YAGO3:<wikicat_Banks_of_the_United_States>",
					 "YAGO3:<wikicat_Investment_banks>")
				.execute();
		
		for (Map.Entry<String, Category> entry : categories.getCategories().entrySet()) {
			System.out.println("- Category: " + entry.getValue().getName() + " (" + entry.getValue().getId() + ")");
		}
		
		System.out.println(LF);
		
		// Knowledge Graph Service - Request metadata from the Categories endpoint
		System.out.println("Querying metadata at the /knowledgegraph/categories/_meta endpoint.");
		
		m = client.knowledgeGraph().categories().getMeta().execute();
		
		System.out.println("- dump version: " + m.getDumpVersion());
		System.out.println("- supported languages: " + m.getLanguages());
		System.out.println("- database creation date: " + m.getCreationDate());
		System.out.println("- collection size: " + numberFormat.format(m.getCollectionSize()));
		
		System.out.println(LF);
	}
	
	/**
	 * Runs some sample requests against both the {@link AmbiverseApiClient#entityLinking() Entity 
	 * Linking Service} and the {@link AmbiverseApiClient#knowledgeGraph() Knowledge
	 * Graph Service}.
	 * 
	 * @throws IOException
	 */
	private void runErrorHandlingSamples() throws IOException {
		System.out.println("*** Running error handling samples ***" + LF);
		
		/* Send a text for which no language is specified. The API endpoint will return an error
		 * message saying that you need to manually set a language for this text segment, as for
		 * this very short text fragment no language can be detected automatically.
		 */
		String text = "ambiverse";
		
		System.out.println("Sent text to /entitylinking/analyze for analysis: \"" + text + "\"");
		
		AnalyzeInput input = new AnalyzeInput().withText(text);
		
		try {
			client.entityLinking().analyze().process(input).execute();
		} catch (ApiException e) {
			System.out.println("Error message: " + e.getMessage());
			System.out.println("Solution: Use setLanguage(String) or withLanguage(String) on the " +
							   "AnalyzeInput object to manually specify the document language.");
		}
		
		System.out.println(LF);
		
		
		/* Send multiple entity IDs to the Knowledge Graph service. If an entity cannot be found,
		 * its ID will be set to null.
		 */
		System.out.println("Querying the knowledge graph at /knowledgegraph/entities for multiple entities.");
		
		String[] entityIDs = new String[] {
				"http://www.wikidata.org/entity/Q93346",		// This entity will be found in the knowledge graph.
				"http://www.wikidata.org/entity/Q0"				// This entity will not be found.
		};
		
		Entities entities = client.knowledgeGraph().entities()
			.get(entityIDs)
			.execute();

		int i = 0;
		for (Map.Entry<String, Entity> entry : entities.getEntities().entrySet()) {
			Entity e = entry.getValue();
			
			if (e.getId() != null) {
				System.out.println("- " + e.getId() + " found. The image URL is " + e.getImage().getUrl());
			} else {
				System.out.println("- " + entityIDs[++i] + " not found in the knowledge graph.");
			}
		}			
	}
	
	/**
	 * Creates and initializes a new instance of {@link AmbiverseApiClient}.
	 * 
	 * @return Initialized instance of {@link AmbiverseApiClient}
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		/* Authenticates your client application against the Ambiverse API endpoint via the OAuth 2
		 * protocol. Your client credentials are read from client_secrets.json on your classpath and
		 * exchanged for an API access token, which is stored within the API client throughout your
		 * session.
		 */
		Credential credential = AmbiverseApiClient.authorize(httpTransport, JSON_FACTORY);
		
		logger.info("Successfully obtained OAuth 2 access token." + LF);
		
		// Instantiate a new API client
		this.client = new AmbiverseApiClient(httpTransport, JSON_FACTORY, credential);
	}
}
