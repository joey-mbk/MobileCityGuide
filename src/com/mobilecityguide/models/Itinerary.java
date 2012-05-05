package com.mobilecityguide.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Itinerary  {
		
	private HashMap<String, String> title;
	private ArrayList<POI> POIList;
	private Category theme;

	public String getTitle(String language) {
		return this.title.get(language);
	}

	public void addTitle(String language, String title) {
		this.title.put(language, title);
	}

	public ArrayList<POI> getPOIList() {
		return POIList;
	}

	public void addPOI(int step, POI poi) {
		this.POIList.add(step, poi);
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

}
