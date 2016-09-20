# Ambiverse Natural Language Understanding API Client Library for Java

- [Overview](#overview)
- [Highlighted Features](#highlighted_features)
- [Getting Started](#getting_started)
- [Documentation](#documentation)
- [Links](#links)

## <a name='overview'>Overview<a/>

The Ambiverse Natural Language Understanding API Client Library for Java is a powerful client library for accessing the [Ambiverse Natural Language Understanding API](https://www.ambiverse.com/natural-language-understanding-api/). The API extracts entities from unstructured text, enabling a more precise transformation of texts into actionable, measurable, and easily accessible knowledge. Entities are identified by types such as person, location, organization, or product, and linked to the Wikipedia-derived YAGO knowledge graph. You can query the knowledge graph to obtain further information about these entities, such as Wikipedia links, textual descriptions, images, and lists of relevant categories.

The client library includes these features:
- Native Java object-oriented access to JSON data models and HTTP-based access methods.
- Authenticates your client application via [OAuth 2](https://tools.ietf.org/html/rfc6749).
- Builds on the [Google APIs Client Library for Java](https://github.com/google/google-api-java-client).

## <a name='highlighted_features'>Highlighted Features<a/>

- **Easily call the Ambiverse Natural Language Understanding API.**
  
  Our API is exposed as HTTP-based endpoints. Dealing with those endpoints in Java code can be cumbersome, as it requires a very good knowledge of how exactly to execute the API calls (setting HTTP request method, adding authentication headers, passing the request payload in the correct format, and so on). The client library abstracts from the low-level API calls and exposes friendly Java objects and methods for interacting with the API. The following code snippet analyzes a text using the [Entity Linking Service](https://developer.ambiverse.com/overview#entity-linking-service).

  ```java
  // Prepare the analysis input, an English text fragment in this case.
  AnalyzeInput input = new AnalyzeInput()
	.withLanguage("en")		// Optional. If not set, language detection happens automatically.
	.withText("When [[Who]] played Tommy in Columbus, Pete was at his best.");

  // Send the input to the /entitylinking/analyze endpoint for analysis.
  AnalyzeOutput output = client.entityLinking().analyze().process(input).execute();
	
  // Iterate through the identified matches which are linked to entities in the knowledge graph.
  for (Match match : output.getMatches()) {
    String text = match.getText();                       // Span of text in the input that was linked.
    Integer charOffset = match.getCharOffset();          // Character offset of the match in the original text.
    Integer charLength = match.getCharLength();          // Character length of the match.
    String entityID = match.getEntity().getId();         // ID of the linked entity, e.g. "YAGO3:<The_Who>".
    double confidence = match.getEntity().getScore();    // Confidence score.
    ...
  }
  ```

- **Work with entities as native Java objects.**

  ```java
  // Query the knowledge graph for details about some entities.
  Entities entities = client.knowledgeGraph().entities()
    .get("YAGO3:<The_Who>", "YAGO3:<Tommy_(album)>", "YAGO3:<Pete_Townshend>")
    .execute();

  // Iterate through the entities.
  for (Entity e : entities.getEntities()) {
    String name = e.getName());                     // Name
    String desc = e.getDescription());              // Description
    Set<String> categories = e.getCategories());    // Categories
    List<Link> links = e.getLinks());               // Links, e.g. to Wikipedia articles
    String imageUrl = e.getImageUrl());             // Image
    ...
  }
  ```

- **Add OAuth 2 authentication to your API calls.**

  The client library reads your OAuth 2 client credentials (that is, your *client id* and *client secret*) from a JSON file in the [format also used by the Google APIs](https://developers.google.com/api-client-library/python/guide/aaa_client_secrets). Once authenticated, your OAuth 2 access token is stored in the `AmbiverseApiClient` instance and applied to subsequent API calls automatically.

  ```java
  // Read the client credentials from client_secrets.json and obtain an OAuth 2 access token.
  Credential credential = AmbiverseApiClient.authorize(httpTransport, JSON_FACTORY);

  // Instantiate a new API client.
  AmbiverseApiClient client = new AmbiverseApiClient(httpTransport, JSON_FACTORY, credential);
  ```
- **Deal with abnormal API responses.**

  Requests may fail due to a variety of reasons (maybe it's just your quota that was exceeded!). To easily deal with any abnormal response, you can catch the `ApiException` (which extends `IOException`). The exception unboxes the JSON error message and gives you insights into what exactly went wrong.
  
  ```java
  /* Send a text for which no language is specified. The API endpoint will return an error
  *  message saying that you need to manually set a language for this text segment, as for
  *  this very short text fragment no language can be detected automatically. */
  AnalyzeInput input = new AnalyzeInput().withText("ambiverse");

  try {
    client.entityLinking().analyze().process(input).execute();
  } catch (ApiException e) {
    logger.severe("Oops! Something went wrong: " + e.getMessage());
  }
  ```
  
## <a name='getting_started'>Getting Started<a/>

To get you started, this project contains a [`HelloApiSample.java`](src/main/java/com/ambiverse/api/sample/client/HelloApiSample.java) class that connects to the Ambiverse Natural Language Understanding API using the Client Library for Java. It holds a couple of fully functional samples that are aligned with the code snippets above so that you can dive right in and build your own applications on top of the API.

1. Make sure that [Apache Maven](https://maven.apache.org/install.html) is installed on your machine, then clone this project locally. All necessary project dependencies will be resolved and downloaded automatically.
2. Register as a developer at [developer.ambiverse.com](https://developer.ambiverse.com/signup). Once you confirm your registration, an OAuth 2 client application will be provisioned for you automatically, and its credentials (that is, *client id* and *client secret*) will be shown to you in the developer portal.  
3. You'll need to add these credentials to the local clone of this project. For that, copy [`client_secrets_template.json`](src/main/resources/client_secrets_template.json) to `src/main/resources/client_secrets.json` and set your client credentials in this file.
4. You're now ready to go! Navigate to the project folder on your local machine, run `mvn exec:java`, and enjoy reading through the API responses.

This is how it works in more detail:

- [`AmbiverseApiClient`](src/main/java/com/ambiverse/api/AmbiverseApiClient.java) **- the client that connects to the Ambiverse Natural Language Understanding API**

  This is the client that exposes the Ambiverse Natural Language Understanding API and operates on native Java objects and methods. It extends [`AbstractGoogleJsonClient`](https://github.com/google/google-api-java-client/blob/dev/google-api-client/src/main/java/com/google/api/client/googleapis/services/json/AbstractGoogleJsonClient.java) and models all API endpoints as native Java methods. To create a new instance of the API Client, add this code to your target class:
  
  ```java
  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
  /** Global instance of the HTTP transport. */
  private static HttpTransport httpTransport = new NetHttpTransport();
  
  private AmbiverseApiClient client = null;
  
  /**
  * Creates and initializes a new instance of {@link AmbiverseApiClient}.
  * 
  * @return Initialized instance of {@link AmbiverseApiClient}
  * @throws IOException
  */
  private void initialize() throws IOException {
    /* 
    * Authenticates your client application against the Ambiverse API endpoint via the OAuth 2
    * protocol. Your client credentials are read from client_secrets.json on your classpath and
    * exchanged for an API access token, which is stored within the API client throughout your
    * session.
    */
    Credential credential = AmbiverseApiClient.authorize(httpTransport, JSON_FACTORY);

    // Instantiate a new API client.
    this.client = new AmbiverseApiClient(httpTransport, JSON_FACTORY, credential);
  }
  ```

- [`HelloApiSample`](src/main/java/com/ambiverse/api/sample/client/HelloApiSample.java) **- the sample code that uses the API Client**

  This class is intended to illustrate how you can build applications on top of the API Client. We added a main method that calls the above initialization in `initialize()`, then runs three sets of samples as shown below. Each of these `run*Samples()` methods is aligned with the code snippets shown in the [Highlighted Features](#highlighted_features) section of this page and will allow you to dive right in.
  
  ```java
  /** Main method for running some sample request against the Ambiverse API endpoint. */
  public static void main(String[] args) throws IOException {
    HelloApiSample helloApi = new HelloApiSample();

    // Initialize the API Client.
    helloApi.initialize();

    // Run some sample requests against the Entity Linking Service.
    helloApi.runEntityLinkingSamples();		

    // Run some sample request against the Knowledge Graph Service.
    helloApi.runKnowledgeGraphSamples();

    // Show how to handle errors.
    helloApi.runErrorHandlingSamples();
  }
  ```
 
## <a name='documentation'>Documentation<a/>
- [API Documentation](https://developer.ambiverse.com/docs)
- [API Overview](https://developer.ambiverse.com/overview)
- [API Product Page](https://www.ambiverse.com/natural-language-understanding-api/)

## <a name='links'>Links<a/>
- [Blog](https://www.ambiverse.com/blog/)
- [Website](https://www.ambiverse.com)

