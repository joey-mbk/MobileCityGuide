package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class POIController {
	
	public static HashMap<Integer, POI> fetchedPOI = new HashMap<Integer, POI>();
	public static POIMapper poiMapper;
	public static HashMap<String, Integer> titlesIDPOIMap = new HashMap<String, Integer>();

	public static POI getPOI(int id) throws Exception {
		return poiMapper.getPOI(id);
	}
	
	public static POI getPOI(String name) throws Exception{
        int id = titlesIDPOIMap.get(name);
		return getPOI(id);
	}
	
	public static ArrayList<String> getCitiesNames() throws Exception{		
		return poiMapper.getCitiesNames();
	}
	
	public static POI[] getPOIOfCity() throws Exception{		
		return poiMapper.getPOIsOfCity(UserController.city);
	}
	
	public static ArrayList<String> getPOINamesOfCity() throws Exception{
		POI[] poiList = poiMapper.getPOIsOfCity(UserController.city);
		ArrayList<String> poiNames = new ArrayList<String>();
		for(POI poi : poiList){
			poiNames.add(getPOIName(poi));
		}
		return poiNames;
	}
	
	public static void sortPOINamesByName(ArrayList<String>POINames){
		Collections.sort(POINames);	
	}
	
	public static void reversePOINames(ArrayList<String>POINames){
		Collections.sort(POINames);
		Collections.reverse(POINames);
	}
	
	public static POI[] getPOIofCategory(Category category) throws Exception{
		return poiMapper.getPOIsOfCategory(UserController.city,category.getId());
		}
	
	public static ArrayList<String> getPOINamesofCategory(Category category) throws Exception{
		POI[] poiList = poiMapper.getPOIsOfCategory(UserController.city,category.getId());
		ArrayList<String> poiNames = new ArrayList<String>();
		for(POI poi : poiList)
			poiNames.add(getPOIName(poi));
		return poiNames;
	}
	
	public static ArrayList<String> getPOINamesofUserCategories() throws Exception{
		ArrayList<String> poiOfUserCategories = new ArrayList<String>();
		for(Category category: UserController.activeUser.getUserCategories()){
			poiOfUserCategories.addAll(POIController.getPOINamesofCategory(category));
		}
		return poiOfUserCategories;
	}
	
	/*
	 * Return poi name according to the active user language
	 */ 
	public static String getPOIName(POI poi){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(poi.getName().containsKey(language))
			titlesIDPOIMap.put(poi.getName(language), poi.getId());
			return poi.getName(language);
		}
		return null;
	}
	
	/*
	 * Return poi description according to the active user language and user age
	 */  
	public static String getPOIDescription(POI poi){
		String [] languages = UserController.activeUser.getLanguage();
		String age = UserController.activeUser.getAge();
		for (String language : languages){
		if(poi.getName().containsKey(language))
			return poi.getDescription(age, language);
		}
		return null;
	}
	
	/*
	 * Return poi location guidelines according to the active user language
	 */ 
	public static String getPOILocationGuidelines(POI poi){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(poi.getName().containsKey(language))
			return poi.getLocationGuidelines(language);
		}
		return null;
	}
	
	/*
	 * Return poi categories titles according to the active user language
	 */ 
	public static ArrayList<String> getPOICategoriesNames(POI poi){
		ArrayList<String> categoriesNames = new ArrayList<String>();
		ArrayList<Category> categories= poi.getcategories();
		for (Category category : categories){
			categoriesNames.add(CategoryController.getCategoryTitle(category.getId()));
		}
		return null;
	}
}
