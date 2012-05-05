package com.mobilecityguide.gateways;

import com.mobilecityguide.models.Itinerary;

public interface ItineraryGateway {

	RecordSet getItinerary(int id) throws Exception;
	RecordSet getItineraryTitles(int id) throws Exception;
	boolean addItinerary(Itinerary itinerary) throws Exception;
	
}
