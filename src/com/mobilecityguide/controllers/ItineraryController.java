package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.mobilecityguide.datamappers.ItineraryMapper;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;

public class ItineraryController {
	public static ItineraryMapper itineraryMapper;
	public static HashMap<Integer, Itinerary> fetchedItineraries = new HashMap<Integer, Itinerary>();
	public static HashMap<String, Integer> titlesIDItinerariesMap = new HashMap<String, Integer>();

	public static Itinerary getItinerary(int id) throws Exception{
		return itineraryMapper.getItinerary(id);
	}

	public static Itinerary getItinerary(String name) throws Exception{
        int id = titlesIDItinerariesMap.get(name);
		return getItinerary(id);
	}


	/*
	 * Return itinerary category title according to the active user language
	 */ 
	public static String getItineraryCategoryName(Itinerary itinerary){		
		return CategoryController.getCategoryTitle(itinerary.getTheme().getId());
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

	/*
	 * Return itinerary title according to the active user language
	 */ 
	public static String getItineraryTitle(int id){
		String [] languages = UserController.activeUser.getLanguage();
		HashMap<String,String> titles = itineraryMapper.getItineraryTitles(id);
		for (String language : languages){
			if(titles.containsKey(language)){
				String title = titles.get(language);
				titlesIDItinerariesMap.put(title, id); //To keep the link between title and id
				return title;
			}
		}
		return null;
	}

	/*
	 * Return itineraries titles according to the active user language
	 */ 
	public static ArrayList<String> getItinerariesTitles(ArrayList<Integer>itinerariesID){
		ArrayList<String>titles = new ArrayList<String>();		
		for(Integer id: itinerariesID)
			titles.add(getItineraryTitle(id));
		return titles;
	}


	public static ArrayList<Integer> getCityItinerariesID() throws Exception{
		return itineraryMapper.getCityItineraries(UserController.city);
	}

	/*
	 * Return city itineraries titles according to the active user language
	 */ 
	public static ArrayList<String> getCityItinerariesTitles() throws Exception{
		return getItinerariesTitles(getCityItinerariesID());
	}
	
	public static ArrayList<Integer> getItineraryOfCategory(ArrayList<Integer>itineraryID, Category category) throws Exception{
		ArrayList<Integer>itinerariesOfCategory = new ArrayList<Integer>();
		for(int id: itineraryID){
			if(itineraryMapper.getItineraryCategory(id).equals(category))
				itinerariesOfCategory.add(id);
		}
		return itinerariesOfCategory;
	}
	
	public static void saveItinerary(Itinerary itinerary){
		try {
			itineraryMapper.addItinerary(itinerary);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
