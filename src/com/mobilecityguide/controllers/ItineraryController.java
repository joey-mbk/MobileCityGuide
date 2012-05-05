package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class ItineraryController {
	public static HashMap<Integer, Itinerary> fetchedItineraries = new HashMap<Integer, Itinerary>();

	/*
	 * Return itinerary category title according to the active user language
	 */ 
	public static String getPOICategoryName(Itinerary itinerary){		
		return CategoryController.getCategoryTitle(itinerary.getTheme());
	}
	
	/*
	 * Return itinerary title according to the active user language
	 */ 
	public static String getItineraryTitle(Itinerary itinerary){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(itinerary.getTitle().containsKey(language))
			return itinerary.getTitle(language);
		}
		return null;
	}
	
}
