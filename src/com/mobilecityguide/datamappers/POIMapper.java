package com.mobilecityguide.datamappers;

import android.content.Context;

import com.mobilecityguide.gateways.POIGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
import com.mobilecityguide.gateways.SQL.SQLPOIGateway;
import com.mobilecityguide.gateways.SQL.SQLUserGateway;
import com.mobilecityguide.models.POI;
import com.mobilecityguide.models.User;

public class POIMapper {

	private POIGateway poiGateway;
	
	public POIMapper(Context context) {
		this.poiGateway = new SQLPOIGateway(context); // instantiate SQL type of User Gateway
	}
	
	/* Fetch a specific POI from the database */
	public POI getPOI(int id) throws Exception {
		
		/* fetch the data from the gateway */
		RecordSet rPoi = poiGateway.getPOI(id);
		RecordSet rCat = poiGateway.getPOICategories(id);
		RecordSet rNames = poiGateway.getPOINames(id);
		RecordSet rDesc = poiGateway.getDescriptions(id);
		RecordSet rLG = poiGateway.getLocGuidelines(id);
		RecordSet rImg = poiGateway.getImages(id);
		
		POI poi = null;
		try {
			if (rPoi.first()) {
				poi = new POI();
				poi.setAddress(rPoi.getString("address"));
				poi.setLatitude(rPoi.getDouble("latitude"));
				poi.setLongitude(rPoi.getDouble("longitude"));
				while (rCat.next())
					poi.addCategory(rCat.getString("language"), rCat.getString("title"));
				while (rNames.next())
					poi.addName(rNames.getString("language"), rNames.getString("title"));
				while (rDesc.next())
					poi.addDescription(rDesc.getString("age"), rDesc.getString("language"), rDesc.getString("description"));
				while (rLG.next())
					poi.addLocationGuidelines(rLG.getString("language"), rLG.getString("locationGuidelines"));
				while (rImg.next())
					poi.addImage(rImg.getString("timeOfDay"), rImg.getString("path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in the RecordSet while getting POI '"+id+"' from the database.");
		}
		return poi;
	}
	
	public POI[] getPOIsOfCity(String city) throws Exception {
		RecordSet r = poiGateway.getPOIsOfCity(city);
		POI[] list = new POI[r.size()];
		int i = 0;
		while (r.next()) {
			list[i] = getPOI(r.getInt("poiID")); // build the POI object and add it to the list
			i++;
		}
		return list;
	}
	
	public POI[] getPOIsOfCategory(String city, int category) throws Exception {
		RecordSet r = poiGateway.getPOIsOfCategory(city, category);
		POI[] list = new POI[r.size()];
		int i = 0;
		while (r.next()) {
			list[i] = getPOI(r.getInt("poiID")); // build the POI object and add it to the list
			i++;
		}
		return list;
	}
	
}
