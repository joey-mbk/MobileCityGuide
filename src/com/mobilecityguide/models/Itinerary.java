package com.mobilecityguide.models;

import java.util.HashMap;

public class Itinerary  {
	
	private int id;
	private HashMap<String, String> title = new HashMap<String,String>();
	private HashMap<Integer,POI> POIList= new HashMap<Integer,POI>();
	private Category theme;

	public HashMap<String, String> getTitle() {
		return this.title;
	}
	
	public String getTitle(String language) {
		return this.title.get(language);
	}

	public void addTitle(String language, String title) {
		this.title.put(language, title);
	}

	public HashMap<Integer,POI> getPOIList() {
		return POIList;
	}

	public void addPOI(int step, POI poi) {
		this.POIList.put(step, poi);
	}

	public Category getTheme() {
		return this.theme;
	}

	public void setTheme(Category theme) {
		this.theme = theme;
	}
	
	public void delPOI(POI poi){
		this.POIList.remove(poi);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
