package com.mobilecityguide.gateways.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.controllers.CityController;
import com.mobilecityguide.gateways.POIGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.models.POI;

public class SQLPOIGateway implements POIGateway {

	private Context context;
	private SQLGateway gw;
	private SQLiteDatabase db;
	
	public SQLPOIGateway(Context context) {
		this.context = context;
		this.gw = new SQLGateway(context);
	}

	@Override
	public RecordSet getPOI(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT P.address, L.latitude, L.longitude FROM POI P, Location L WHERE P.poiID = '"+id+"' AND L.locationID = P.locationID";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting POI '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getPOICategories(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT CT.title, CT.language FROM CategoryTitles CT, POICategory PC WHERE PC.poiID = '"+id+"' AND CT.categoryID = PC.categoryID";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting categories of POI '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getPOINames(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT title, language FROM POITitles WHERE poiID = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting titles of POI '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getDescriptions(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT description, language, age FROM Description WHERE poiID = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting descriptions of POI '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getLocGuidelines(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT locationGuidelines, language FROM LocationGuideLines WHERE poiID = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting location guidelines of POI '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getImages(int id) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT timeOfDay, path FROM Image WHERE poiID = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting images of POI '"+id+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getPOIsOfCity(String city) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT poiID FROM POI WHERE  city = '"+city+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting POIs of city '"+city+"' from database.");
		}
		return results;
	}

	@Override
	public RecordSet getPOIsOfCategory(String city, int category) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT poiID FROM POICategory WHERE categoryID = '"+category+"' AND poiID IN (SELECT poiID FROM POI WHERE city = '"+city+"')";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting POIs of category '"+category+"' in city '"+city+"' from database.");
		}
		return results;
	}

}
