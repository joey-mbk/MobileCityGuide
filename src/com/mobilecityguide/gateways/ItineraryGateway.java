package com.mobilecityguide.gateways;

public interface ItineraryGateway {

	RecordSet getItinerary(int id) throws Exception;
	RecordSet getItineraryTitles(int id) throws Exception;
	
}
