package com.ambiverse.api.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "charLength",
    "charOffset",
    "text",
    "entity"
})
public class Match {

    /**
     * The character length of the match in the text.
     * 
     */
	@Key
    @JsonProperty("charLength")
    private Integer charLength;
    
    /**
     * The character offset of the match in the text, starting at 0.
     * 
     */
	@Key
    @JsonProperty("charOffset")
    private Integer charOffset;
    
    /**
     * The full text of the match (equivalent to the substring of the text defined by charOffset and charLength).
     * 
     */
	@Key
    @JsonProperty("text")
    private String text;
    
	@Key
    @JsonProperty("entity")
    private EntityReference entity;
    
    /**
     * The character length of the match in the text.
     * 
     * @return
     *     The charLength
     */
    @JsonProperty("charLength")
    public Integer getCharLength() {
        return charLength;
    }

    /**
     * The character length of the match in the text.
     * 
     * @param charLength
     *     The charLength
     */
    @JsonProperty("charLength")
    public void setCharLength(Integer charLength) {
        this.charLength = charLength;
    }

    public Match withCharLength(Integer charLength) {
        this.charLength = charLength;
        return this;
    }

    /**
     * The character offset of the match in the text, starting at 0.
     * 
     * @return
     *     The charOffset
     */
    @JsonProperty("charOffset")
    public Integer getCharOffset() {
        return charOffset;
    }

    /**
     * The character offset of the match in the text, starting at 0.
     * 
     * @param charOffset
     *     The charOffset
     */
    @JsonProperty("charOffset")
    public void setCharOffset(Integer charOffset) {
        this.charOffset = charOffset;
    }

    public Match withCharOffset(Integer charOffset) {
        this.charOffset = charOffset;
        return this;
    }

    /**
     * The full text of the match (equivalent to the substring of the text defined by charOffset and charLength).
     * 
     * @return
     *     The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * The full text of the match (equivalent to the substring of the text defined by charOffset and charLength).
     * 
     * @param text
     *     The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    public Match withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * 
     * @return
     *     The entity
     */
    @JsonProperty("entity")
    public EntityReference getEntity() {
        return entity;
    }

    /**
     * 
     * @param entity
     *     The entity
     */
    @JsonProperty("entity")
    public void setEntity(EntityReference entity) {
        this.entity = entity;
    }

    public Match withEntity(EntityReference entity) {
        this.entity = entity;
        return this;
    }
    
    @Override
    public String toString() {
    	return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
