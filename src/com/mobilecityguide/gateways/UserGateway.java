package com.mobilecityguide.gateways;

import com.mobilecityguide.exceptions.GatewayException;

public interface UserGateway {

	RecordSet getUser(String name) throws Exception;

}
