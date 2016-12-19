package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.*;
import com.google.api.client.util.Key;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "charLength",
    "charOffset",
})
public class AnnotatedMention {
	
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
	 * The character length of the match in the text.
	 *
	 * @return The charLength
	 */
	@JsonProperty("charLength")
	public Integer getCharLength() {
		return charLength;
	}

	/**
	 * The character length of the match in the text.
	 *
	 * @param charLength
	 *            The charLength
	 */
	@JsonProperty("charLength")
	public void setCharLength(Integer charLength) {
		this.charLength = charLength;
	}

	public AnnotatedMention withCharLength(Integer charLength) {
		this.charLength = charLength;
		return this;
	}

	/**
	 * The character offset of the match in the text, starting at 0.
	 *
	 * @return The charOffset
	 */
	@JsonProperty("charOffset")
	public Integer getCharOffset() {
		return charOffset;
	}

	/**
	 * The character offset of the match in the text, starting at 0.
	 *
	 * @param charOffset
	 *            The charOffset
	 */
	@JsonProperty("charOffset")
	public void setCharOffset(Integer charOffset) {
		this.charOffset = charOffset;
	}

	public AnnotatedMention withCharOffset(Integer charOffset) {
		this.charOffset = charOffset;
		return this;
	}
}
