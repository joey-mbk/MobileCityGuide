package com.mobilecityguide.models;

import java.util.HashMap;

public class Category {
	
	private int id;
	private HashMap <String, String> category;
	
	public Category(int ID, HashMap <String, String> category){
		id = ID;
		this.category= category;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<String, String> getCategory() {
		return category;
	}
	
	public String getCategory(String language) {
		return category.get(language);
	}
	
	public void setCategory(HashMap<String, String> category) {
		this.category = category;
	}
	
	public String toString() {
		return this.getCategory("english");
	}
}
