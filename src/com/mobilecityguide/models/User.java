package com.mobilecityguide.models;

import java.util.ArrayList;

public class User {
	
	private String name;
	private String[] language;
	private String age;
	private ArrayList<Itinerary> userItineraryList;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		this.language = language;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public ArrayList<Itinerary> getUserItineraryList() {
		return userItineraryList;
	}

	public void setUserItineraryList(ArrayList<Itinerary> userItineraryList) {
		this.userItineraryList = userItineraryList;
	}

}
