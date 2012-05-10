package com.mobilecityguide.gateways.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.User;

public class SQLUserGateway implements UserGateway {

	private SQLGateway gw;
	private SQLiteDatabase db;

	public SQLUserGateway(Context context) {
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
		try {
			db.delete("User","userName = '"+name+"'", null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean saveUser(User user, String previousName){
		try {
			deleteUser(previousName);
			addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addUser(User user){
		if (this.db.isReadOnly())
			this.db = this.gw.getWritableDatabase(); // re-open DB in write mode

		ContentValues cv1 = new ContentValues();
		cv1.put("userName", user.getName());
		cv1.put("age", user.getAge());
		try {
			db.insert("User",null,cv1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error cv1");
			return false;
		}

		String [] languagesArray = user.getLanguage();
		for(int i=0;i<languagesArray.length;i++){
			ContentValues cv2 = new ContentValues();
			cv2.put("userName", user.getName());
			cv2.put("priority", i+1);
			cv2.put("language", languagesArray[i]);
			try {
				db.insert("Language",null,cv2);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error cv2");
				return false;
			}
		}

		for(Category category : user.getUserCategories()) {
			ContentValues cv3 = new ContentValues();
			cv3.put("categoryID", category.getId());
			cv3.put("userName", user.getName());
			try {
				db.insert("UserCategory",null,cv3);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error cv3");
				return false;
			}	
		}

		if(!user.getUserItinerariesID().isEmpty()){
			for(int itineraryID : user.getUserItinerariesID()) {
				ContentValues cv4 = new ContentValues();
				cv4.put("itineraryID", itineraryID);
				cv4.put("userName", user.getName());
				try {
					db.insert("UserItinerary",null,cv4);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error cv4");
					return false;
				}	
			}
		}
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

	public RecordSet getAllLanguages(){
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT DISTINCT language FROM Language";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


}