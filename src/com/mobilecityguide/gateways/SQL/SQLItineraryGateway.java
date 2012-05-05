package com.mobilecityguide.gateways.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.gateways.ItineraryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.models.Itinerary;

public class SQLItineraryGateway implements ItineraryGateway {

	private Context context;
	private SQLGateway gw;
	private SQLiteDatabase db;
	
	public SQLItineraryGateway(Context context) {
		this.context = context;
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
			
		String query = "INSERT INTO User VALUES ('"+user.getName()+"','"+user.getAge()+"')";
		try {
			db.rawQuery(query, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
