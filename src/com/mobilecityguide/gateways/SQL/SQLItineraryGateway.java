package com.mobilecityguide.gateways.SQL;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.gateways.ItineraryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class SQLItineraryGateway implements ItineraryGateway {

	private SQLGateway gw;
	private SQLiteDatabase db;

	public SQLItineraryGateway(Context context) {
		this.gw = new SQLGateway(context);
	}

	@Override
	public RecordSet getItinerary(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT I.poiID, I.step, IC.categoryID FROM POIItinerary I, ItineraryCategory IC WHERE I.itineraryID = '"+id+"' AND IC.itineraryID = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting itinerary '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getItineraryTitles(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT language, title FROM ItineraryTitles WHERE itineraryID = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting titles of itinerary '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public boolean addItinerary(Itinerary itinerary) throws Exception {
		if (this.db.isReadOnly())
			this.db = this.gw.getWritableDatabase(); // re-open DB in write mode

		/* Retrieve the id of the last added itinerary first
		 * so we can attach the POIs to it in the database */
		RecordSet results = null;
		//String getLastKey = "SELECT last_insert_rowid() as last_key";
		String getLastKey = "SELECT DISTINCT itineraryID from POIItinerary";
		try {
			results = new SQLSet(db.rawQuery(getLastKey, null));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		ArrayList<Integer> idList = new ArrayList<Integer>();
        while(results.next()){
        	idList.add(results.getInt("itineraryID"));
        }
        Collections.sort(idList);
		//int lastKey = results.getInt("last_key");
        int lastKey = idList.get(idList.size()-1);
		HashMap<Integer,POI> poiList = itinerary.getPOIList();
		for (int i = 1; i <= poiList.size(); i++) {
			ContentValues cv1 = new ContentValues();
			cv1.put("itineraryID", lastKey+1);
			cv1.put("poiID", poiList.get(i-1).getId());
			cv1.put("step",i);
			try {
				db.insert("POIItinerary",null,cv1);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		ContentValues cv2 = new ContentValues();
		cv2.put("itineraryID", lastKey+1);
		cv2.put("categoryID", itinerary.getTheme().getId());
		try {
			db.insert("ItineraryCategory",null,cv2);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		for(Entry<String, String> entry : itinerary.getTitle().entrySet()) {
			ContentValues cv3 = new ContentValues();
			cv3.put("itineraryID", lastKey+1);
			cv3.put("language", entry.getKey());
			cv3.put("title", entry.getValue());
			try {
				db.insert("ItineraryTitles",null,cv3);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
		// add to UserItinerary table
		ContentValues cv = new ContentValues();
		cv.put("itineraryID", lastKey+1);
		cv.put("userName", UserController.activeUser.getName());
		try {
			db.insert("UserItinerary",null,cv);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public RecordSet getItineraryCategory(int id){
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT IC.categoryID, CT.language, CT.title FROM ItineraryCategory IC, CategoryTitles CT WHERE IC.itineraryID = '"+id+"' AND IC.categoryID=CT.categoryID";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public RecordSet getCityItineraries(String city) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT DISTINCT itineraryID FROM POIItinerary WHERE  poiID IN (SELECT poiID FROM POI WHERE city = '"+city+"')";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean deleteItinerary(int id) {
		if (this.db.isReadOnly())
			this.db = this.gw.getWritableDatabase(); // re-open DB in write mode
		try {
			db.delete("ItineraryTitles","itineraryID = '"+id+"'", null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean saveItinerary(Itinerary itinerary) {
		try {
			deleteItinerary(itinerary.getId());
			addItinerary(itinerary);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
