package com.ambiverse.api.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;


public class MatchEntity {

  @Key
  @JsonProperty("id")
  private String id;

  @Key
  @JsonProperty("confidence")
  private Double confidence;

  public String getId() {
    return id;
  }

  public void setKgId(String id) {
    this.id = id;
  }


  public Double getConfidence() {
    return confidence;
  }

  public void setConfidence(Double score) {
    this.confidence = score;
  }

  @Override public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
