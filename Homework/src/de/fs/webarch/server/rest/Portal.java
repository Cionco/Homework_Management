package de.fs.webarch.server.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Portal {

	private String id;
	private String title;
	private String summary;
	private String description;
	
	// Bug1 ==> RestExceptionMapper
//	public Portal() {
//	}
	
	public Portal(String id, String title) {
		this.title=title;
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
