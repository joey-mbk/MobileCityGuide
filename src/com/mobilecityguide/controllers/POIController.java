package com.mobilecityguide.controllers;

import java.util.HashMap;

import android.content.Context;

import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.models.POI;

public class POIController {
	
	public static HashMap<Integer, POI> fetchedPOI = new HashMap<Integer, POI>();
	public static POIMapper poiMapper;

	public static POI getPOI(int id) throws Exception {
		return poiMapper.getPOI(id);
	}
}
