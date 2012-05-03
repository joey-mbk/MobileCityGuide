package com.mobilecityguide.gateways.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;

public class SQLUserGateway implements UserGateway {
	
	private Context context;
	
	public SQLUserGateway(Context context) {
		this.context = context;
	}
	
	public RecordSet getUser(String name) throws Exception {
		String query = "SELECT U.age, L.language, L.priority FROM User U, Language L WHERE U.userName = '"+name+"' AND L.userName = U.userName";
		SQLSet results = null;
		try {
			SQLGateway gw = new SQLGateway(context);
			SQLiteDatabase db = gw.getReadableDatabase();
			results = new SQLSet(db.rawQuery(query, null));
			//db.close(); // ne fonctionne pas :/
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting user '"+name+"' from database.");
		}
		return results;
	}
}