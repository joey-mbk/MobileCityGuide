package com.mobilecityguide.gateways;

import com.mobilecityguide.exceptions.GatewayException;

public interface UserGateway {

	RecordSet getUser(String name) throws Exception;
	boolean deleteUser(String name);
	boolean editUser(String name, String col, String val);

}
