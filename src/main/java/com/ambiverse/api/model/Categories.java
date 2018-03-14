package com.ambiverse.api.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;

public class Categories {

  @Key
  @JsonProperty("categories")
  private Map<String, Category> categories = new HashMap<>();

  /**
   *
   * (Required)
   *
   * @return
   *     The categories
   */
  @JsonProperty("categories")
  public Map<String, Category> getCategories() {
    return categories;
  }

  /**
   *
   * (Required)
   *
   * @param categories
   *     The categories
   */
  @JsonProperty("categories")
  public void setCategories(Map<String, Category> categories) {
    this.categories = categories;
  }

  public Categories withCategories(Map<String, Category> categories) {
    this.categories = categories;
    return this;
  }

  @Override public String toString() {
    return "[" + StringUtils.join(categories.keySet(), ", ") + "]";
  }

}
