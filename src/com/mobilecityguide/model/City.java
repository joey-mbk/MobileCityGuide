package com.mobilecityguide.model;

import java.util.List;

public class City {
	
	private String name;
	
	private List<POI> listPOI;
	
	private List<Itinerary> itineraryList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<POI> getListPOI() {
		return listPOI;
	}

	public void setListPOI(List<POI> listPOI) {
		this.listPOI = listPOI;
	}

	public List<Itinerary> getItineraryList() {
		return itineraryList;
	}

	public void setItineraryList(List<Itinerary> itineraryList) {
		this.itineraryList = itineraryList;
	}
	
	public void delPOI(){
		
	}
	
	public void addPOI(){
		
	}
	
	public void delItinerary(){
		
	}
	
	public void addItinerary() {
		
	}
	
	public void sortPOIList(String param){
		
	}
	
	public void sortItineraryList(String param){
		
	}

}
