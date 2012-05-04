package com.mobilecityguide.gateways.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.gateways.CategoryGateway;
import com.mobilecityguide.gateways.RecordSet;

public class SQLCategoryGateway implements CategoryGateway{
	
	private Context context;
	private SQLGateway gw;
	private SQLiteDatabase db;

	public SQLCategoryGateway(Context context) {
		this.context = context;
		this.gw = new SQLGateway(context);
	}

	public RecordSet getCategoryID(String categoryName, String language){
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT categoryID FROM CategoryTitles WHERE title = '"+categoryName+"' AND language = '"+language+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
