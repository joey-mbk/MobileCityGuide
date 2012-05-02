package com.mobilecityguide.gateways.SQL;

import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.UserGateway;

public class SQLUserGateway extends SQLGateway implements UserGateway {
	public RecordSet getUser(String name) throws GatewayException {
		String query = "SELECT U.age, L.language, L.priority FROM User U, Language L WHERE U.userName = '"+name+"' AND L.userName = U.userName";
		SQLSet results = null;
		try {
			results = new SQLSet(executeQuery(query));
		} catch (GatewayException e) {
			throw new GatewayException("Error while getting user '"+name+"' from database.");
		}
		return results;
	}
}