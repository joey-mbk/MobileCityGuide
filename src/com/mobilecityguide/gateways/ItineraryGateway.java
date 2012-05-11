package com.mobilecityguide.gateways;

import com.mobilecityguide.models.Itinerary;

public interface ItineraryGateway {

	RecordSet getItinerary(int id) throws Exception;
	RecordSet getItineraryTitles(int id) throws Exception;
	boolean addItinerary(Itinerary itinerary) throws Exception;
	RecordSet getItineraryCategory(int id) throws Exception;
	RecordSet getCityItineraries(String city) throws Exception;
	boolean deleteItinerary(int id);
	public boolean saveItinerary(Itinerary itinerary);
	public RecordSet getPredefCityItineraries(String city) throws Exception;
	public RecordSet getLastItineraryID();
	
}
