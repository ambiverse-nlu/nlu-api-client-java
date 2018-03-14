package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;


/**
 * Author
 */
public class Author   {

  @Key
  @JsonProperty("name")
  private String name;

  @Key
  @JsonProperty("url")
  private String url;


  /**
   * The name of the image author.
   * @return name
   **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Author withName(String name) {
    this.name = name;
    return this;
  }

  /**
   * URL of the user page of the image author in Wikipedia.
   * @return url
   **/
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Author withUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

