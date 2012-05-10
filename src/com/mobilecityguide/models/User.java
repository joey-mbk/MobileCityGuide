package com.mobilecityguide.models;

import java.util.ArrayList;

public class User {
	
	private String name;
	private String[] language;
	private String age;
	private ArrayList<Category> userCategories;
	private ArrayList<Integer> userItinerariesID;
	
	public User(){
		this.userCategories= new ArrayList<Category>();
		this.userItinerariesID = new ArrayList<Integer>();
	}
	
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

	public void addCategory(Category category) {
		this.userCategories.add(category);
	}
	
	public void delCategory(Category category) {
		this.userCategories.remove(category);
	}
	
	public ArrayList<Category> getUserCategories() {
		return userCategories;
	}

	public void setUserCategories(ArrayList<Category> userCategories) {
		this.userCategories = userCategories;
	}
	
	public ArrayList<Integer> getUserItinerariesID() {
		return userItinerariesID;
	}

	public void setUserItinerariesID(ArrayList<Integer> userItinerariesID) {
		this.userItinerariesID = userItinerariesID;
	}
}
