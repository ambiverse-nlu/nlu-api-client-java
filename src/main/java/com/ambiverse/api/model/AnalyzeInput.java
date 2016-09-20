package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "docId",
    "language",
    "text",
    "confidenceThreshold",
    "coherentDocument"
})
public class AnalyzeInput {

    /**
     * Will be part of the response so that you can identify your documents.
     * 
     */
	@Key
    @JsonProperty("docId")
    private String docId;
    
    /**
     * Language of the input text.
     * 
     */
    @Key
    @JsonProperty("language")
    private String language;
    
    /**
     * The natural-language text to analyze.
     * (Required)
     * 
     */
    @Key
    @JsonProperty("text")
    private String text;
    
    /**
     * Filters every entity with a confidence score lower than the threshold (in [0.0,1.0]).
     * 
     */
    @Key
    @JsonProperty("confidenceThreshold")
    private Double confidenceThreshold;
    
    /**
     * Our method by default assumes that the document is coherent, i.e. the entities in it are related to each other. Set this to false if the document contains very different types of entities that are not related to each other.
     * 
     */
    @Key
    @JsonProperty("coherentDocument")
    private Boolean coherentDocument;

    /**
     * Will be part of the response so that you can identify your documents.
     * 
     * @return
     *     The docId
     */
    @JsonProperty("docId")
    public String getDocId() {
        return docId;
    }

    /**
     * Will be part of the response so that you can identify your documents.
     * 
     * @param docId
     *     The docId
     */
    @JsonProperty("docId")
    public void setDocId(String docId) {
        this.docId = docId;
    }

    public AnalyzeInput withDocId(String docId) {
        this.docId = docId;
        return this;
    }

    /**
     * Language of the input text.
     * 
     * @return
     *     The language
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * Language of the input text.
     * 
     * @param language
     *     The language
     */
    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public AnalyzeInput withLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * The natural-language text to analyze.
     * (Required)
     * 
     * @return
     *     The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * The natural-language text to analyze.
     * (Required)
     * 
     * @param text
     *     The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    public AnalyzeInput withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Filters every entity with a confidence score lower than the threshold (in [0.0,1.0]).
     * 
     * @return
     *     The confidenceThreshold
     */
    @JsonProperty("confidenceThreshold")
    public Double getConfidenceThreshold() {
        return confidenceThreshold;
    }

    /**
     * Filters every entity with a confidence score lower than the threshold (in [0.0,1.0]).
     * 
     * @param confidenceThreshold
     *     The confidenceThreshold
     */
    @JsonProperty("confidenceThreshold")
    public void setConfidenceThreshold(Double confidenceThreshold) {
        this.confidenceThreshold = confidenceThreshold;
    }

    public AnalyzeInput withConfidenceThreshold(Double confidenceThreshold) {
        this.confidenceThreshold = confidenceThreshold;
        return this;
    }

    /**
     * Our method by default assumes that the document is coherent, i.e. the entities in it are related to each other. Set this to false if the document contains very different types of entities that are not related to each other.
     * 
     * @return
     *     The coherentDocument
     */
    @JsonProperty("coherentDocument")
    public Boolean getCoherentDocument() {
        return coherentDocument;
    }

    /**
     * Our method by default assumes that the document is coherent, i.e. the entities in it are related to each other. Set this to false if the document contains very different types of entities that are not related to each other.
     * 
     * @param coherentDocument
     *     The coherentDocument
     */
    @JsonProperty("coherentDocument")
    public void setCoherentDocument(Boolean coherentDocument) {
        this.coherentDocument = coherentDocument;
    }

    public AnalyzeInput withCoherentDocument(Boolean coherentDocument) {
        this.coherentDocument = coherentDocument;
        return this;
    }
}
