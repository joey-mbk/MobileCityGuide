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
	
	public POI getPOI(int id) throws Exception {
		RecordSet r = poiGateway.getPOI(id); // fetch the results from the gateway
		POI poi = null;
		try {
			if (r.first()) {
				poi = new POI();
				poi.setAddress(r.getString("address"));
				poi.setCategory();
				poi.setName();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in the RecordSet while getting POI '"+id+"' from the database.");
		}
		return poi;
	}
	
}
