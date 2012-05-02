package com.mobilecityguide.model;

import java.util.List;

public class User {
	
	private String name;
	
	private List<String> language;
	
	private String age;
	
	private List<Itinerary> userItineraryList;
	
	private City city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLanguage() {
		return language;
	}

	public void setLanguage(List<String> language) {
		this.language = language;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<Itinerary> getUserItineraryList() {
		return userItineraryList;
	}

	public void setUserItineraryList(List<Itinerary> userItineraryList) {
		this.userItineraryList = userItineraryList;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
