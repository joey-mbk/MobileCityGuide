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

	public void addNextPOI(POI poi) {
		this.POIList.put(POIList.keySet().size()+1, poi);
	}
	
	public void addPOI(int step ,POI poi) {
		this.POIList.put(step, poi);
	}

	public Category getTheme() {
		return this.theme;
	}

	public void setTheme(Category theme) {
		this.theme = theme;
	}
	
	public void delPOI(int step){
		this.POIList.remove(step);
		for(int i = step+1;i<=POIList.keySet().size()+1;i++){
			POI poi = POIList.get(i);
			this.POIList.remove(i);
			this.POIList.put(i-1,poi);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void reOrder(int currentStep, int newStep) {

		POI currentStepPOI = this.POIList.get(currentStep);
		POI newStepPOI = this.POIList.get(newStep);
		this.POIList.remove(currentStep);
		this.POIList.remove(newStep);
		this.POIList.put(currentStep, newStepPOI);
		this.POIList.put(newStep, currentStepPOI);
		
		
		/*	POI poi = this.POIList.get(currentStep); // get the poi we wish to reorder
		this.POIList.remove(currentStep); // remove the poi from the list
		POI newStepPOI = this.POIList.get(newStep);
		POI currentStepPOI = this.POIList.get(currentStep);
		this.POIList.remove(currentStep);
		POI tempPOI = null;
		POI tempPOI2 = null;
		
		if (currentStep > newStep) {
			tempPOI2 = currentStepPOI;
			
			for (int i = newStep; i <= this.POIList.size()+1; i++) {
				if (i != this.POIList.size()+1) {
					tempPOI = this.POIList.get(i);
					this.POIList.remove(i);
				}
				this.POIList.put(i, tempPOI2);
				tempPOI2 = tempPOI;
			}
		}
		
		if (currentStep < newStep) {
			for (int i = currentStep+1; i <= newStep; i++) {
				tempPOI = this.POIList.get(i);
				this.POIList.remove(i);
				this.POIList.put(i-1, tempPOI);
			}
			
			this.POIList.put(new Integer(newStep), currentStepPOI);
		}*/
	}

}
