package com.accenture.findmyapp.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Person {

	int id; 
	public int getHoyde() {
		return hoyde;
	}
	public void setHoyde(int hoyde) {
		this.hoyde = hoyde;
	}
	String name;
	int hoyde;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
