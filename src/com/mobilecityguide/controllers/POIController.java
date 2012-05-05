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
	 * Return poi name in active user language
	 */ 
	public static String getPOIName(POI poi){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(poi.getName().containsKey(language))
			return poi.getName(language);
		}
		return null;
	}
}
