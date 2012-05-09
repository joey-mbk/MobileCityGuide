package com.mobilecityguide.datamappers;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.gateways.ItineraryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.SQL.SQLItineraryGateway;
import com.mobilecityguide.models.Category;
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
				itinerary.setId(id);
				itinerary.setTheme(getItineraryCategory(id));
				while (rT.next()) // add titles of itinerary in different languages
					itinerary.addTitle(rT.getString("language"), rT.getString("title"));
				if(!rIt.equals(null)){
				do { // add each POI of the itinerary
					POI poi = POIController.getPOI(rIt.getInt("poiID"));
					itinerary.addPOI(rIt.getInt("step"), poi); // add POI at the right place (step) in the POI list of itinerary

				} while (rIt.next());
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

	public HashMap<String,String>getItineraryTitles(int id){
		RecordSet r;
		HashMap<String, String> titlesMap = new HashMap<String, String>();
		try {
			r = itineraryGateway.getItineraryTitles(id);


			while (r.next())
				titlesMap.put(r.getString("language"), r.getString("title"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titlesMap;
	}
	public Category getItineraryCategory(int id) throws Exception{
		HashMap<String, String> categoryMap = new HashMap<String, String>();
		RecordSet r = itineraryGateway.getItineraryCategory(id);
		Category category = null;
		try {
			if (r.first()) {
				int catID = r.getInt("categoryID");
				while (r.next())
					categoryMap.put(r.getString("language"), r.getString("title"));

				category = new Category(catID, categoryMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	public ArrayList<Integer> getCityItineraries(String city) throws Exception {
		ArrayList<Integer> itineraries = new ArrayList<Integer>();
		RecordSet r = itineraryGateway.getCityItineraries(city);
		try {
			while (r.next())
				itineraries.add(new Integer(r.getInt("itineraryID")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itineraries;
	}
	
	public void deleteItinerary(Itinerary itinerary) {
		itineraryGateway.deleteItinerary(itinerary.getId());
	}
	
	public void saveItinerary(Itinerary itinerary) {
		itineraryGateway.saveItinerary(itinerary);
	}

}
