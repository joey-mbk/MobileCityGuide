package com.mobilecityguide.datamappers;

import java.util.ArrayList;

import android.content.Context;

import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
import com.mobilecityguide.gateways.SQL.SQLUserGateway;
import com.mobilecityguide.models.User;

public class UserMapper {
	
	private UserGateway userGateway;
	
	public UserMapper(Context context) {
		this.userGateway = new SQLUserGateway(context); // instantiate SQL type of User Gateway
	}
	
	public User getUser(String name) throws Exception {
		RecordSet r = userGateway.getUser(name); // fetch the results from the gateway
		User user = null;
		try {
			if (r.first()) {
				user = new User();
				user.setName(name);
				user.setAge(r.getString("age"));
				user.setLanguage(r.getList("language", "priority"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in the RecordSet while getting user '"+name+"' from the database.");
		}
		return user;
	}
	
	public boolean deleteUser(String name) {
		return this.userGateway.deleteUser(name);
	}
	
	public boolean save(User user) {
		return this.userGateway.saveUser(user);
	}
	
	public boolean setUserName(User user, String previousName) {
		return this.userGateway.setUserName(user, previousName);
	}
	
	public boolean addUser(User user) {
		return this.userGateway.addUser(user);
	}
	
	public ArrayList<String> getAllUsersNames(){
		RecordSet r = userGateway.getAllUser(); // fetch the results from the gateway
		ArrayList<String> usersNames = new ArrayList<String>();
		try {
		     //TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersNames;
	}
}
