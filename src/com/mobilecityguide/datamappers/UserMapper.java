package com.mobilecityguide.datamappers;

import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;
import com.mobilecityguide.models.User;

public class UserMapper {
	
	private UserGateway userGateway;
	
	public User getUser(String name) throws GatewayException, RecordSetException {
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
			throw new RecordSetException("Error in the RecordSet while getting user '"+name+"' from the database.");
		}
		return user;
	}
}
