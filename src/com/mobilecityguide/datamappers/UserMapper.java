package com.mobilecityguide.datamappers;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
import com.mobilecityguide.gateways.SQL.SQLUserGateway;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.User;

public class UserMapper {

	private UserGateway userGateway;

	public UserMapper(Context context) {
		this.userGateway = new SQLUserGateway(context); // instantiate SQL type of User Gateway
	}

	public User getUser(String name) throws Exception {
		RecordSet rU = userGateway.getUser(name); // fetch the results from the gateway
		RecordSet rCat = userGateway.getUserCategories(name);
		RecordSet rI = userGateway.getUserItinerariesID(name);
		User user = null;
		try {
			if (rU.first()) {
				user = new User();
				user.setName(name);
				user.setAge(rU.getString("age"));
				user.setLanguage(rU.getList("language", "priority"));
			}
			HashMap<String, String> categoriesMap = new HashMap<String,String>();
			int id = 0;
			while (rCat.next())
			{
				if(id==0)
				id = rCat.getInt("categoryID");
				if(id!=rCat.getInt("categoryID")){
				Category category = new Category(id, categoriesMap);
				user.addCategory(category);
				categoriesMap = new HashMap<String, String>();
				id = rCat.getInt("categoryID");
				}
				categoriesMap.put(rCat.getString("language"), rCat.getString("title"));
			}
			Category category = new Category(id, categoriesMap);
			user.addCategory(category);
			
			while (rI.next())
			{
				user.getUserItinerariesID().add(rI.getInt("itineraryID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in the RecordSet while getting user '"+name+"' from the database.");
		}
		rU.close();
		rCat.close();
		rI.close();
		return user;
	}

	public boolean deleteUser(String name) {
		return this.userGateway.deleteUser(name);
	}

	public boolean save(User user, String previousName) {
		return this.userGateway.saveUser(user, previousName);
	}

	public boolean addUser(User user) {
		return this.userGateway.addUser(user);
	}

	public ArrayList<String> getAllUsersNames(){
		RecordSet r = userGateway.getAllUser(); // fetch the results from the gateway
		ArrayList<String> usersNames = new ArrayList<String>();
		try {
			while (r.next())
				usersNames.add(r.getString("userName"));
		} catch (Exception e) {
			e.printStackTrace();
			r.close();
		}
		r.close();
		return usersNames;
	}

	public ArrayList<String> getAllLanguages(){
		RecordSet r = userGateway.getAllLanguages();
		ArrayList<String> languages = new ArrayList<String>();
		try {
			while(r.next())
				languages.add(r.getString("language"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		r.close();
		return languages;
	}
}
