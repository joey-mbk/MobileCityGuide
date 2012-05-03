package com.mobilecityguide.gateways.SQL;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
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
		return true;
	}

	public boolean addUser(User user){
		String query1 = "INSERT INTO User VALUES '"+user.getName()+"','"+user.getAge()+"'";
		try {
			db.rawQuery(query1, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String [] languagesArray = user.getLanguage();
		for(int i=0;i<languagesArray.length;i++){			
			String query = "INSERT INTO Language VALUES '"+user.getName()+"','"+i+"','"+languagesArray[i]+"'";
			try {
				db.rawQuery(query, null);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		ArrayList<String> categoriesArray = user.getUserCategoryList();
		Iterator<String> categoriesIterator = categoriesArray.iterator();
		while(categoriesIterator.hasNext()){
			//TODO
			/*	String query = "INSERT INTO UserCategory VALUES '"+getCategoryID(categoriesIterator.next())+"','"+user.getName()+"'";
			try {
				db.rawQuery(query, null);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}*/	
		}			
		return true;
	}

	public boolean delUser(User user){
		String query1 = "DELETE FROM User WHERE userName ='"+user.getName();
		try {
			db.rawQuery(query1, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String query2 = "DELETE FROM Language WHERE userName =  '"+user.getName();
		try {
			db.rawQuery(query2, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String query3 = "DELETE FROM UserItinerary WHERE userName =  '"+user.getName();
		try {
			db.rawQuery(query3, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String query4 = "DELETE FROM UserCategory WHERE userName =  '"+user.getName();
		try {
			db.rawQuery(query4, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public RecordSet getAllUser(){
		this.db = this.gw.getReadableDatabase();
		String query = "SELECT U.userName FROM User U";
		SQLSet results = null;
		try {
			results = new SQLSet(db.rawQuery(query, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


}