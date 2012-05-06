package com.mobilecityguide.gateways.SQL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.User;

public class SQLUserGateway implements UserGateway {

	private Context context;
	private SQLGateway gw;
	private SQLiteDatabase db;

	public SQLUserGateway(Context context) {
		this.context = context;
		this.gw = new SQLGateway(context);
	}

	public RecordSet getUser(String name) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT U.age, L.language, L.priority FROM User U, Language L WHERE U.userName = '"+name+"' AND L.userName = U.userName";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting user '"+name+"' from database.");
		}
		return results;
	}
	
	public RecordSet getUserCategories(String name) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT CT.categoryID, CT.title, CT.language FROM CategoryTitles CT, UserCategory U WHERE U.userName = '"+name+"' AND CT.categoryID = U.categoryID";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting categories of User '"+name+"' from database.");
		}
		return results;
	}
	
	public RecordSet getUserItinerariesID(String name) throws Exception {
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT itineraryID FROM UserItinerary UI WHERE userName = '"+name+"'";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while getting itineraries of User '"+name+"' from database.");
		}
		return results;
	}


	public boolean deleteUser(String name) {
		if (this.db.isReadOnly())
			this.db = this.gw.getWritableDatabase(); // re-open DB in write mode
		String query = "DELETE FROM User WHERE userName = '"+name+"'";
		try {
			db.rawQuery(query, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean setUserName(User user, String previousName){
		if (this.db.isReadOnly())
			this.db = this.gw.getWritableDatabase(); // re-open DB in write mode
		String query = "UPDATE User SET userName = '"+user.getName()+"' WHERE userName = '"+previousName+"'";
		try {
			db.rawQuery(query, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveUser(User user){
		if (this.db.isReadOnly())
			this.db = this.gw.getWritableDatabase(); // re-open DB in write mode

		String [] languagesArray = user.getLanguage();
		for(int i=0;i<languagesArray.length;i++){			
			String query = "UPDATE Language SET language = '"+languagesArray[i]+"' WHERE userName = '"+user.getName()+"' AND priority = '"+i+"'";
			try {
				db.rawQuery(query, null);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		String query2 = "UPDATE User SET age = '"+user.getAge()+"' WHERE userName = '"+user.getName()+"'";
		try {
			db.rawQuery(query2, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		//TODO Useritineraies Usercategories
		return true;
	}

	public boolean addUser(User user){
		if (this.db.isReadOnly())
		this.db = this.gw.getWritableDatabase(); // re-open DB in write mode
		
		String query1 = "INSERT INTO User VALUES ('"+user.getName()+"','"+user.getAge()+"')";
		try {
			db.rawQuery(query1, null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error query1");
			return false;
		}

		String [] languagesArray = user.getLanguage();
		for(int i=0;i<languagesArray.length;i++){			
			String query2 = "INSERT INTO Language VALUES ('"+user.getName()+"',"+i+",'"+languagesArray[i]+"')";
			try {
				db.rawQuery(query2, null);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		for(Category category : user.getUserCategories()) {
		    String query3 = "INSERT INTO UserCategory VALUES ('"+category.getId()+"','"+user.getName()+"')";
					try {
				db.rawQuery(query3, null);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
		//TODO itinéraire + regrouper
		return true;
	}

	public RecordSet getAllUser(){
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT userName FROM User";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


}