package com.ambiverse.api.model;

import java.util.*;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.client.util.Key;
import com.google.api.client.util.Value;


public class Entity {

  @Key
  @JsonProperty("id")
  private String id;

  /**
   * The most salient entity type.
   */
  public enum TypeEnum {
    @Value("UNKNOWN")
    UNKNOWN("UNKNOWN"),

    @Value("PERSON")
    PERSON("PERSON"),

    @Value("LOCATION")
    LOCATION("LOCATION"),

    @Value("ORGANIZATION")
    ORGANIZATION("ORGANIZATION"),

    @Value("EVENT")
    EVENT("EVENT"),

    @Value("ARTIFACT")
    ARTIFACT("ARTIFACT"),

    @Value("OTHER")
    OTHER("OTHER");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override @JsonValue public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @Key
  @JsonProperty("type")
  private TypeEnum type;

  @Key
  @JsonProperty("names")
  private Map<String, Label> names = new HashMap<>();

  @Key
  @JsonProperty("descriptions")
  private Map<String, Label> descriptions = new HashMap<>();

  @Key
  @JsonProperty("detailedDescriptions")
  private Map<String, Label> detailedDescriptions;

  @Key
  @JsonProperty("image")
  private Image image = null;

  @Key
  @JsonProperty("links")
  private Map<String, Label> links = new HashMap<>();

  @Key
  @JsonProperty("categories")
  private Set<String> categories = null;

  public Entity withId(String id) {
    this.id = id;
    return this;
  }

  /**
   * Knowledge graph ID of the entity.
   * @return id
   **/
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Entity withType(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The most salient entity type.
   * @return type
   **/
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Entity withNames(Map<String, Label> names) {
    this.names = names;
    return this;
  }

  public Entity putNamesItem(String key, Label namesItem) {
    this.names.put(key, namesItem);
    return this;
  }

  /**
   * a (language, label) map. &#x60;en&#x60; is an example key.
   * @return names
   **/
  @JsonProperty("names")
  public Map<String, Label> getNames() {
    return names;
  }

  public void setNames(Map<String, Label> names) {
    this.names = names;
  }

  public Entity withDescriptions(Map<String, Label> descriptions) {
    this.descriptions = descriptions;
    return this;
  }

  public Entity putDescriptionsItem(String key, Label descriptionsItem) {
    this.descriptions.put(key, descriptionsItem);
    return this;
  }

  /**
   * a (language, label) map. &#x60;en&#x60; is an example key.
   * @return descriptions
   **/
  @JsonProperty("descriptions")
  public Map<String, Label> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(Map<String, Label> descriptions) {
    this.descriptions = descriptions;
  }

  public Entity withDetailedDescriptions(Map<String, Label> detailedDescriptions) {
    this.detailedDescriptions = detailedDescriptions;
    return this;
  }

  public Entity putDetailedDescriptionsItem(String key, Label detailedDescriptionsItem) {
    if (this.detailedDescriptions == null) {
      this.detailedDescriptions = new HashMap<String, Label>();
    }
    this.detailedDescriptions.put(key, detailedDescriptionsItem);
    return this;
  }

  /**
   * a (language, label) map. &#x60;en&#x60; is an example key.
   * @return detailedDescriptions
   **/
  @JsonProperty("detailedDescriptions")
  public Map<String, Label> getDetailedDescriptions() {
    return detailedDescriptions;
  }

  public void setDetailedDescriptions(Map<String, Label> detailedDescriptions) {
    this.detailedDescriptions = detailedDescriptions;
  }

  public Entity withImage(Image image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
   **/
  @JsonProperty("image")
  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public Entity withLinks(Map<String, Label> links) {
    this.links = links;
    return this;
  }

  public Entity putLinksItem(String key, Label linksItem) {
    this.links.put(key, linksItem);
    return this;
  }

  /**
   * a (language, label) map. &#x60;en&#x60; is an example key and value is an external links to different sources
   * @return links
   **/
  @JsonProperty("links")
  public Map<String, Label> getLinks() {
    return links;
  }

  public void setLinks(Map<String, Label> links) {
    this.links = links;
  }

  public Entity withCategories(Set<String> categories) {
    this.categories = categories;
    return this;
  }

  public Entity addCategoriesItem(String categoriesItem) {
    if (this.categories == null) {
      this.categories = new HashSet<String>();
    }
    this.categories.add(categoriesItem);
    return this;
  }

  /**
   * List of categories, whereby each category is referenced by its knowledge graph ID.
   * @return categories
   **/
  @JsonProperty("categories")
  public Set<String> getCategories() {
    return categories;
  }

  public void setCategories(Set<String> categories) {
    this.categories = categories;
  }

  @Override public String toString() {
    return this.getId();
  }

}
