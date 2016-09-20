package com.ambiverse.api.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.api.client.util.Key;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "categories"
})
public class Categories {

	@Key
    @JsonProperty("categories")
    private List<Category> categories = new ArrayList<Category>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The categories
     */
    @JsonProperty("categories")
    public List<Category> getCategories() {
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
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public Categories withCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public String toString() {
    	return "[" + StringUtils.join(categories, ", ") + "]";
    }

}
