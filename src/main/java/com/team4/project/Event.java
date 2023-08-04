package com.team4.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="EVENTS")
public class Event {

	@Id
	private String id;
	
	private String code;
	
	private String description, title;

	public Event() {
		
	}
	
	public Event(String code, String description, String title) {
		this.code = code;
		this.description = description;
		this.title = title;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	
}

