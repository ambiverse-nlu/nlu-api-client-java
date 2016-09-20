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
    "entities"
})
public class Entities {

	@Key
    @JsonProperty("entities")
    private List<Entity> entities = new ArrayList<Entity>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The entities
     */
    @JsonProperty("entities")
    public List<Entity> getEntities() {
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
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    
    public Entities withEntities(List<Entity> entities) {
        this.entities = entities;
        return this;
    }
    
    public void addEntity(Entity entity) {
    	entities.add(entity);
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
    	return "[" + StringUtils.join(entities, ", ") + "]";
    }

}
