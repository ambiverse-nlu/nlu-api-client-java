package com.ambiverse.api.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "dumpVersion",
    "languages",
    "creationDate",
    "collectionSize"
})
public class Meta {

	@Key
    @JsonProperty("dumpVersion")
    private String dumpVersion;
	
	@Key
    @JsonProperty("languages")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    private Set<String> languages = new LinkedHashSet<String>();
	
	// TODO Comment in next line once the date is ISO 8601 compliant.
	@Key
    @JsonProperty("creationDate")
    private DateTime creationDate;
    	
	@Key
    @JsonProperty("collectionSize")
    private Integer collectionSize;
    
    
    /**
     * 
     * @return
     *     The dumpVersion
     */
    @JsonProperty("dumpVersion")
    public String getDumpVersion() {
        return dumpVersion;
    }

    /**
     * 
     * @param dumpVersion
     *     The dumpVersion
     */
    @JsonProperty("dumpVersion")
    public void setDumpVersion(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }

    public Meta withDumpVersion(String dumpVersion) {
        this.dumpVersion = dumpVersion;
        return this;
    }

    /**
     * 
     * @return
     *     The languages
     */
    @JsonProperty("languages")
    public Set<String> getLanguages() {
        return languages;
    }

    /**
     * 
     * @param languages
     *     The languages
     */
    @JsonProperty("languages")
    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public Meta withLanguages(Set<String> languages) {
        this.languages = languages;
        return this;
    }

    /**
     * 
     * @return
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public DateTime getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Meta withCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * 
     * @return
     *     The collectionSize
     */
    @JsonProperty("collectionSize")
    public Integer getCollectionSize() {
        return collectionSize;
    }

    /**
     * 
     * @param collectionSize
     *     The collectionSize
     */
    @JsonProperty("collectionSize")
    public void setCollectionSize(Integer collectionSize) {
        this.collectionSize = collectionSize;
    }

    public Meta withCollectionSize(Integer collectionSize) {
        this.collectionSize = collectionSize;
        return this;
    }

    @Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }    
    
}
