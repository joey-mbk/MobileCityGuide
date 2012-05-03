package com.mobilecityguide.gateways.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.controllers.CityController;
import com.mobilecityguide.gateways.POIGateway;
import com.mobilecityguide.gateways.RecordSet;

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
		String query = "SELECT * FROM POI WHERE id = '"+id+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting POI '"+id+"' from database.");
		}
		return results;
	}

}
