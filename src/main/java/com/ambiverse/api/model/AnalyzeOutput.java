package com.ambiverse.api.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;

public class AnalyzeOutput {

  /**
   * Document ID from the input.
   */
  @Key
  @JsonProperty("docId")
  private String docId;

  /**
   * Language of the text. In case the language is not provided on input, the language is detected automatically, otherwise its the same as the input.
   */
  @Key
  @JsonProperty("language")
  private String language;

  /**
   * Matches found in the text.
   */
  @Key
  @JsonProperty("matches")
  private List<Match> matches = new ArrayList<Match>();

  /**
   * All entities found in the text.
   */
  @Key
  @JsonProperty("entities")
  private Set<OutputEntity> entities = new HashSet<>();

  /**
   *
   * @return
   *     Document ID from the input.
   */
  @JsonProperty("docId")
  public String getDocId() {
    return docId;
  }

  /**
   *
   * @param docId
   *     Document ID from the input.
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
   *     Language of the text. In case the language is not provided on input, the language is detected automatically, otherwise its the same as the input.
   */
  @JsonProperty("language")
  public String getLanguage() {
    return language;
  }

  /**
   *
   * @param language
   *     Language of the text. In case the language is not provided on input, the language is detected automatically, otherwise its the same as the input.
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

  /**
   *
   * @return
   *     The entities
   */
  @JsonProperty("entities")
  public Set<OutputEntity> getEntities() {
    return entities;
  }

  /**
   *
   * @param entities
   */
  @JsonProperty("entities")
  public void setEntities(Set<OutputEntity> entities) {
    this.entities = entities;
  }

  public AnalyzeOutput withEntities(Set<OutputEntity> entities) {
    this.entities = entities;
    return this;
  }

  @Override public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
