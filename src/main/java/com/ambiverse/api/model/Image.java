package com.ambiverse.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Image
 */
public class Image   {
  @Key
  @JsonProperty("url")
  private String url;

  @Key
  @JsonProperty("licences")
  private List<Licence> licences;

  @Key
  @JsonProperty("author")
  private Author author = null;

  public Image withUrl(String url) {
    this.url = url;
    return this;
  }

  /**
   * The public url of the image
   * @return url
   **/
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Image withLicences(List<Licence> licences) {
    this.licences = licences;
    return this;
  }

  public Image addLicencesItem(Licence licencesItem) {
    if (this.licences == null) {
      this.licences = new ArrayList<Licence>();
    }
    this.licences.add(licencesItem);
    return this;
  }

  /**
   * Get licences
   * @return licences
   **/
  @JsonProperty("licences")
  public List<Licence> getLicences() {
    return licences;
  }

  public void setLicences(List<Licence> licences) {
    this.licences = licences;
  }

  public Image withAuthor(Author author) {
    this.author = author;
    return this;
  }

  /**
   * The image author.
   * @return author
   **/
  @JsonProperty("author")
  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    licences: ").append(toIndentedString(licences)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
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

