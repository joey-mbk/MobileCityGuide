package com.mobilecityguide.datamappers;

import android.content.Context;

import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.gateways.ItineraryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.SQL.SQLItineraryGateway;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class ItineraryMapper {
	
	private ItineraryGateway itineraryGateway;
	
	public ItineraryMapper(Context context) {
		this.itineraryGateway = new SQLItineraryGateway(context); // instantiate SQL type of User Gateway
	}
	
	/* Fetch a specific itinerary from the database */
	public Itinerary getItinerary(int id) throws Exception {
		
		/* if itinerary has already been fetched from the database, return it */
		Itinerary itinerary = ItineraryController.fetchedItineraries.get(new Integer(id));
		if (itinerary != null)
			return itinerary;
		
		/* fetch the data from the gateway */
		RecordSet rIt = itineraryGateway.getItinerary(id);
		RecordSet rT = itineraryGateway.getItineraryTitles(id);
		
		try {
			if (rIt.first()) {
				itinerary = new Itinerary();
				itinerary.setTheme(CategoryController.getCategory(id));
				while (rT.next()) // add titles of itinerary in different languages
					itinerary.addTitle(rT.getString("language"), rT.getString("title"));
				while (rIt.next()) { // add each POI of the itinerary
					POI poi = POIController.getPOI(rIt.getInt("poiID"));
					itinerary.addPOI(rIt.getInt("step"), poi); // add POI at the right place (step) in the POI list of itinerary
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in the RecordSet while getting POI '"+id+"' from the database.");
		}
		ItineraryController.fetchedItineraries.put(new Integer(id), itinerary);
		return itinerary;
	}
	
	public boolean addItinerary(Itinerary itinerary) throws Exception {
		return this.itineraryGateway.addItinerary(itinerary);
	}
	
}
