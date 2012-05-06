package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.POI;

public class POIController {
	
	public static HashMap<Integer, POI> fetchedPOI = new HashMap<Integer, POI>();
	public static POIMapper poiMapper;

	public static POI getPOI(int id) throws Exception {
		return poiMapper.getPOI(id);
	}
	
	public static ArrayList<String> getCitiesNames() throws Exception{		
		return poiMapper.getCitiesNames();
	}
	
	public static ArrayList<String> getPOINamesOfCity() throws Exception{
		POI[] poiList = poiMapper.getPOIsOfCity(UserController.city);
		ArrayList<String> poiNames = new ArrayList<String>();
		for(POI poi : poiList){
			poiNames.add(getPOIName(poi));
		}
		return poiNames;
	}
	
	public static ArrayList<String> getPOIofCategory(Category category) throws Exception{
		POI[] poiList = poiMapper.getPOIsOfCategory(UserController.city,category.getId());
		ArrayList<String> poiNames = new ArrayList<String>();
		for(POI poi : poiList)
			poiNames.add(getPOIName(poi));
		return poiNames;
	}
	
	/*
	 * Return poi name according to the active user language
	 */ 
	public static String getPOIName(POI poi){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(poi.getName().containsKey(language))
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
			categoriesNames.add(CategoryController.getCategoryTitle(category));
		}
		return null;
	}
}
