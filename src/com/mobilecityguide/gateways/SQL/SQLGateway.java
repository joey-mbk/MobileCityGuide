package com.mobilecityguide.gateways.SQL;

import java.sql.*;

import com.mobilecityguide.exceptions.GatewayException;

public class SQLGateway {
	public ResultSet executeQuery(String query) throws GatewayException {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:assets/database.sqlite");
			PreparedStatement statement = conn.prepareStatement(query);
			return statement.executeQuery();
		} catch (Exception e) {
			throw new GatewayException("Error while executing a query on the database.");
		}
	}
}
