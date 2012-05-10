package com.mobilecityguide.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.mobilecityguide.R;

public class RoutePath extends MapActivity{

	/** Called when the activity is first created. */ 

	MapView mapView; 

	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.directions); 

		MapView mapView = (MapView) findViewById(R.id.myMapView1); 
		double src_lat = 25.04202; // the testing source 
		double src_long = 121.534761; 
		double dest_lat = 25.05202; // the testing destination 
		double dest_long = 121.554761; 
		GeoPoint srcGeoPoint = new GeoPoint((int) (src_lat * 1E6), 
				(int) (src_long * 1E6)); 
		GeoPoint destGeoPoint = new GeoPoint((int) (dest_lat * 1E6), 
				(int) (dest_long * 1E6)); 

		DrawPath(srcGeoPoint, destGeoPoint, Color.GREEN, mapView); 

		mapView.getController().animateTo(srcGeoPoint); 
		mapView.getController().setZoom(15); 

	} 

	@Override 
	protected boolean isRouteDisplayed() { 
		// TODO Auto-generated method stub 
		return false; 
	} 

	private void DrawPath(GeoPoint src, GeoPoint dest, int color, 
			MapView mMapView01) { 

		// code in section 2.2 

	} 

}


