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
		String [] languages = UserController.activeUser.getLanguage();
		for(POI poi : poiList){
			poiNames.add(poi.getName(languages[0]));
		}
		return poiNames;
	}
	
	public static ArrayList<String> getPOIofCategory(Category category) throws Exception{
		POI[] poiList = poiMapper.getPOIsOfCategory(UserController.city,category.getId());
		ArrayList<String> poiNames = new ArrayList<String>();
		String [] languages = UserController.activeUser.getLanguage();
		for(POI poi : poiList){
			poiNames.add(poi.getName(languages[0]));
		}
		return poiNames;
	}
}
