package com.mobilecityguide.datamappers;

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
		// TODO UserController
		return this.userGateway.deleteUser(name);
	}
	
	public boolean editUser(String name, String col, String val) {
		return this.userGateway.editUser(name, col, val);
	}
}
