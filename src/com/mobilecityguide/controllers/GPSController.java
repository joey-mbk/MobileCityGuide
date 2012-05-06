package com.mobilecityguide.controllers;

import java.util.Iterator;
import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;

public class GPSController {//implements LocationListener{
/*
	private LocationManager	locationManager;
	private double userLatitude;
	private double userLongitude;

	public GPSController(LocationManager locationManager){
		this.locationManager=locationManager;
	}
	
	public double getDistance(POI poi){
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);
		}
		else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
		    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, this);
		}
		Location location = new Location("");
		return Math.sqrt(Math.pow(userLatitude-poi.getLatitude(),2) + Math.pow(userLongitude-poi.getLongitude(),2));//TODO eviter de faire ce calcul s'il ne faut pas
	}

	public POI getClosest(Itinerary itinerary){
		List<POI>POIList = itinerary.getPOIList();
		Iterator<POI> poiIter = POIList.iterator();
		POI closestPOI = poiIter.next();
		
		while(poiIter.hasNext()){
			POI poi2 = poiIter.next();
			if(getDistance(closestPOI)>getDistance(poi2))
				closestPOI = poi2;
		}
		
		return closestPOI;
	}

	@Override
	public final void onLocationChanged(Location location)	{
		userLatitude = location.getLatitude();
		userLongitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
*/
}
