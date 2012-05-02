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
		// TODO
	}
	
	public void addPOI(){
		// TODO
	}
	
	public void delItinerary(){
		// TODO
	}
	
	public void addItinerary() {
		// TODO
	}
	
	public void sortPOIList(String param){
		// TODO
	}
	
	public void sortItineraryList(String param){
		// TODO
	}

}
