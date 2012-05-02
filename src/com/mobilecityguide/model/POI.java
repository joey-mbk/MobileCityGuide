package com.mobilecityguide.model;

import java.util.Dictionary;
import java.util.List;

public class POI  {
	
	private String name;

	private Dictionary<String,String> description;

	private String address;
	
	private Double latitude;
	
	private Double longitude;
	
	private List<String> images;
	
	private String openingsHours;
	
	private List<String> category;


	
	public String getName(String language) {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Dictionary<String,String> getDescription(String age,String language) {
		return description;
	}


	public void setDescription(Dictionary<String,String> description) {
		this.description = description;
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


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public String getOpeningsHours() {
		return openingsHours;
	}


	public void setOpeningsHours(String openingsHours) {
		this.openingsHours = openingsHours;
	}

	public List<String> getCategory(String language) {
		return category;
	}


	public void setCategory(List<String> category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
	//private GeoPoint geoPoint; //the object used by Android for representing a geographical coordinate
/*
	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}
*/
	

}
