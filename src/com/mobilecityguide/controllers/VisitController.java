package com.mobilecityguide.controllers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class VisitController {

	private boolean reverse;
	private boolean finished;

	private final double MIN_DISTANCE = 0; 

/*
	public void playTour(Itinerary itinerary){

		List<POI> POIList = itinerary.getPOIList();

		if(reverse)
			Collections.sort(POIList, Collections.reverseOrder());
		else
			Collections.sort(POIList);

		Iterator<POI> poiIter = POIList.iterator();
		POI poi = poiIter.next();

		setFinished(false);
		while(!isFinished()){
			if (MobileCityGuideActivity.gps_controller.getDistance(poi)<=MIN_DISTANCE){
				//System.out.println("Near "+poi.getName(???.getActiveUser().getLanguage));
				if(poiIter.hasNext())
					poi=poiIter.next();
				else
					setFinished(true);
			}
		}
	}

	public void playFreeWalk(Itinerary itinerary){

		setFinished(false);
		while(!isFinished()){
			POI closestPOI = MobileCityGuideActivity.gps_controller.getClosest(itinerary);
			if (MobileCityGuideActivity.gps_controller.getDistance(closestPOI)<=MIN_DISTANCE){
				//System.out.println("Near "+closestPOI.getName(???.getActiveUser().getLanguage));
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
