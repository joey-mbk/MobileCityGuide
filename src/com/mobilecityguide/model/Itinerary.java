package com.mobilecityguide.model;

import java.util.List;

public class Itinerary  {
		
	private List<String> title;
	private List<POI>POIList;
	private List<String> theme;

	public List<String> getTitle(String language) {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<POI> getPOIList() {
		return POIList;
	}

	public void setPOIList(List<POI> pOIList) {
		POIList = pOIList;
	}

	public List<String> getTheme(String language) {
		return theme;
	}

	public void setTheme(List<String> theme) {
		this.theme = theme;
	}
	
	public void delPOI(){
		// TODO
	}
	
	public void addPOI(){
		// TODO
	}
	

}
