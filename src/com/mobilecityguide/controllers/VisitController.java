package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class VisitController {
/*
	private boolean reverse;
	private boolean finished;

	private final double MIN_DISTANCE = 10; 

	public void playTour(Itinerary itinerary){

		ArrayList<POI> POIList = itinerary.getPOIList();

		if(reverse)
			Collections.reverse(POIList);
		
       	Iterator<POI> poiIter = POIList.iterator();
		POI poi = poiIter.next();
        
		for(int i = 0; i<POIList.size(); i++)
		{
		setFinished(false);
		while(!isFinished()){
			if (GPSController.getDistance(poi)<=MIN_DISTANCE){
				System.out.println("Near "+POIController.getPOIName(poi));
				if(poiIter.hasNext())
					poi=poiIter.next();
				else
					setFinished(true);
			}
		}
		}
	}

	public void playFreeWalk(Itinerary itinerary){

		setFinished(false);
		while(!isFinished()){
			POI closestPOI = MobileCityGuideActivity.gps_controller.getClosest(itinerary);
			if (MobileCityGuideActivity.gps_controller.getDistance(closestPOI)<=MIN_DISTANCE){
				System.out.println("Near "+POIController.getPOIName(closestPOI));
				
			}
		}
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void finishVisit(){
		finished = true;
	}
*/
}
