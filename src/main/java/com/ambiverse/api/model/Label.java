package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;


/**
 * Label
 */
public class Label {

  /**
   * The language code of the label
   */
  @Key
  @JsonProperty("language")
  private String language;

  /**
   * The text of the label in a specific language
   */
  @Key
  @JsonProperty("value")
  private String value;

  public Label language(String language) {
    this.language = language;
    return this;
  }

  /**
   * The language code of the label
   * @return language
   **/
  @JsonProperty("language")
  public String getLanguage() {
    return language;
  }

  public Label withLanguage(String language) {
    this.language = language;
    return this;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Label value(String value) {
    this.value = value;
    return this;
  }

  /**
   * The text of the label in a specific language
   * @return value
   **/
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Label withValue(String value) {
    this.value = value;
    return this;
  }
}

