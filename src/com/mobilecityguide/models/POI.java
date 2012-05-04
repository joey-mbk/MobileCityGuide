package com.mobilecityguide.models;

import java.util.HashMap;

public class POI {
	
	private String address;
	private HashMap<String, String> category; // key is language, value is category title
	private HashMap<String, String> name; // key is language, value is POI name
	private HashMap<String, HashMap<String, String>> description; // key is language, value is another hashmap whose key is language and value is the description
	private HashMap<String, String> locGuidelines; // key is language, value is guidelines
	private double latitude;
	private double longitude;
	private HashMap<String, String> images; // key is time of day, value is image path
	private String openingsHours; // missing in the DB !!!
	
	public POI() {
		/* Initialize all the hashmaps */
		this.category = new HashMap<String, String>();
		this.name = new HashMap<String, String>();
		this.description = new HashMap<String, HashMap<String, String>>();
		this.locGuidelines = new HashMap<String, String>();
		this.images = new HashMap<String, String>();
	}
	
	public String getName(String language) {
		return this.name.get(language);
	}

	public void addName(String language, String name) {
		this.name.put(language, name);
	}

	public String getDescription(String age, String language) {
		if (this.description.containsKey(age)) {
			return this.description.get(age).get(language);
		}
		else
			return null;
	}

	public void addDescription(String age, String language, String description) {
		if (this.description.containsKey(age))
			this.description.get(age).put(language, description);
		else {
			HashMap<String, String> desc = new HashMap<String, String>();
			desc.put(language, description);
			this.description.put(age, desc);
		}
	}
	
	public String getLocationGuidelines(String language) {
		return this.locGuidelines.get(language);
	}
	
	public void addLocationGuidelines(String language, String locGuidelines) {
		this.locGuidelines.put(language, locGuidelines);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getImages(String timeOfDay) {
		return this.images.get(timeOfDay);
	}

	public void addImage(String timeOfDay, String path) {
		this.images.put(timeOfDay, path);
	}

	public String getOpeningsHours() {
		return openingsHours;
	}

	public void setOpeningsHours(String openingsHours) {
		this.openingsHours = openingsHours;
	}

	public String getCategory(String language) {
		return this.category.get(language);
	}

	public void addCategory(String language, String title) {
		this.category.put(language, title);
	}
}
