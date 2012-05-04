package com.mobilecityguide.gateways;

import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.models.User;

public interface UserGateway {

	RecordSet getUser(String name) throws Exception;
	RecordSet getUserCategories(String name) throws Exception;
	boolean deleteUser(String name);
	boolean saveUser(User user);
	boolean setUserName(User user, String previousName);
	boolean addUser(User user);
	RecordSet getAllUser();

}
