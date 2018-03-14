package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.*;
import com.google.api.client.util.Key;
import com.google.api.client.util.Value;
import com.google.common.base.Objects;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class OutputEntity {
  /**
   * The knowledge graph ID of the entity. Query the https://api.ambiverse.com/knowledgegraph/ API for additional knowledge stored about this entity.
   *
   */
  @Key
  @JsonProperty("id")
  private String id;

  /**
   * The URL of the entity. Usualy this is Wikipedia URL.
   *
   */
  @Key
  @JsonProperty("url")
  private String url;

  /**
   * The salience score of the entity in a range of [0,1]. The salience score provides information about the importance of the entity to the text.
   * Score closer to 1 means that the entity is more salient, while score closer to 0 means less salient.
   */
  @Key
  @JsonProperty("salience")
  private Double salience;


  @Key
  @JsonProperty("name")
  private String name;

  @Key
  @JsonProperty("type")
  private Type type;


  public enum Type {
    @Value("UNKNOWN")
    UNKNOWN,
    @Value("PERSON")
    PERSON,
    @Value("LOCATION")
    LOCATION,
    @Value("ORGANIZATION")
    ORGANIZATION,
    @Value("ARTIFACT")
    ARTIFACT,
    @Value("EVENT")
    EVENT,
    @Value("OTHER")
    OTHER
  }

  public OutputEntity() {
    // TODO Auto-generated constructor stub
  }

  /**
   * The knowledge graph ID of the entity. Query the https://api.ambiverse.com/knowledgegraph/ API for additional knowledge stored about this entity.
   *
   * @return
   *     The id
   */
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  /**
   * The URL of the entity. Usualy this is Wikipedia URL.
   *
   * @return
   *  The url
   */
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  /**
   * The knowledge graph ID of the entity. Query the https://api.ambiverse.com/knowledgegraph/ API for additional knowledge stored about this entity.
   *
   * @param id
   *     The id
   */
  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The URL of the entity. Usualy this is Wikipedia URL.
   *
   * @param url
   *     The url
   */
  @JsonProperty("url") public void setUrl(String url) {
    this.url = url;
  }

  public OutputEntity withId(String id) {
    this.id = id;
    return this;
  }

  /**
   * The salience score of the entity in a range of [0,1]. The salience score provides information about the importance of the entity to the text.
   * Score closer to 1 means that the entity is more salient, while score closer to 0 means less salient.
   *
   * @return
   *     The confidence
   */
  @JsonProperty("salience")
  public Double getSalience() {
    return salience;
  }

  /**
   * The salience score of the entity in a range of [0,1]. The salience score provides information about the importance of the entity to the text.
   * Score closer to 1 means that the entity is more salient, while score closer to 0 means less salient.
   *
   * @param salience
   *     The salience
   */
  @JsonProperty("salience")
  public void setSalience(Double salience) {
    this.salience = salience;
  }

  public OutputEntity withSalience(Double salience) {
    this.salience = salience;
    return this;
  }

  /**
   * The most salient type of the entity.
   *
   * @return
   *     The type
   */
  @JsonProperty("type")
  public Type getType() {
    return type;
  }

  /**
   * TThe most salient type of the entity.
   *
   * @param type
   *     The type
   */
  @JsonProperty("type")
  public void setType(Type type) {
    this.type = type;
  }

  public OutputEntity withType(Type type) {
    this.type = type;
    return this;
  }

  /**
   * The name of the entity.
   *
   * @return
   *     The name
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  /**
   * The name of the entity
   *
   * @param name
   *     The name
   */
  @JsonProperty("name") public void setName(String name) {
    this.name = name;
  }

  public OutputEntity withName(String name) {
    this.name = name;
    return this;
  }


  @Override public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final OutputEntity other = (OutputEntity) obj;

    return Objects.equal(this.id, other.id);
  }

}
