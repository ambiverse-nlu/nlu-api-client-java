package com.ambiverse.api.model;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;


public class Entities {

  @Key
  @JsonProperty("entities")
  private Map<String, Entity> entities;

  /**
   *
   * (Required)
   *
   * @return
   *     The entities
   */
  @JsonProperty("entities")
  public Map<String, Entity> getEntities() {
    return entities;
  }

  /**
   *
   * (Required)
   *
   * @param entities
   *     The entities
   */
  @JsonProperty("entities")
  public void setEntities(Map<String, Entity> entities) {
    this.entities = entities;
  }

  public Entities withEntities(Map<String, Entity> entities) {
    this.entities = entities;
    return this;
  }

  public void addEntity(Entity entity) {
    entities.put(entity.getId(), entity);
  }

  public static Entities fromString(String... yagoIDs) {
    Entities entities = new Entities();

    for (String yagoID : yagoIDs) {
      entities.addEntity(new Entity().withId(yagoID));
    }

    return entities;
  }

  @Override
  public String toString() {
    return "[" + StringUtils.join(entities.keySet(), ", ") + "]";
  }

}
