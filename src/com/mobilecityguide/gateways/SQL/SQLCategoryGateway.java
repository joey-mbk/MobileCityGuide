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

	@Override
	public RecordSet getCategory(int ID) {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT language, title FROM CategoryTitles WHERE categoryID = '"+ID+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public RecordSet getAllCategories() {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT language, title FROM CategoryTitles";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
