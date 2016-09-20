package com.ambiverse.api.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "docId",
    "language",
    "matches"
})
public class AnalyzeOutput {

	@Key
    @JsonProperty("docId")
    private String docId;
    
	@Key
    @JsonProperty("language")
    private String language;
    
	@Key
    @JsonProperty("matches")
    private List<Match> matches = new ArrayList<Match>();
    
    /**
     * 
     * @return
     *     The docId
     */
    @JsonProperty("docId")
    public String getDocId() {
        return docId;
    }

    /**
     * 
     * @param docId
     *     The docId
     */
    @JsonProperty("docId")
    public void setDocId(String docId) {
        this.docId = docId;
    }

    public AnalyzeOutput withDocId(String docId) {
        this.docId = docId;
        return this;
    }

    /**
     * 
     * @return
     *     The language
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public AnalyzeOutput withLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * 
     * @return
     *     The matches
     */
    @JsonProperty("matches")
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * 
     * @param matches
     *     The matches
     */
    @JsonProperty("matches")
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public AnalyzeOutput withMatches(List<Match> matches) {
        this.matches = matches;
        return this;
    }
    
    @Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
