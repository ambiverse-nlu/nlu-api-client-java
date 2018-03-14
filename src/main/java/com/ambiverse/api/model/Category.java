package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;

import java.util.Map;

public class Category {


  @Key
  @JsonProperty("id")
  private String id;


  @Key
  @JsonProperty("name")
  private String name;

  @JsonProperty("descriptions")
  private Map<String, Label> descriptions;

  /**
   *
   * @return
   *     The id
   */
  @JsonProperty("id") public String getId() {
    return id;
  }

  /**
   *
   * @param id
   *     The id
   */
  @JsonProperty("id") public void setId(String id) {
    this.id = id;
  }

  public Category withId(String id) {
    this.id = id;
    return this;
  }

  /**
   *
   * (Required)
   *
   * @return
   *     The name
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   *
   * (Required)
   *
   * @param name
   *     The name
   */
  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  public Category withName(String name) {
    this.name = name;
    return this;
  }

  @JsonProperty("descriptions")
  public Map<String, Label> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(Map<String, Label> descriptions) {
    this.descriptions = descriptions;
  }

  public Category withDescriptions(Map<String, Label> descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  @Override public String toString() {
    return this.getId();
  }

}
