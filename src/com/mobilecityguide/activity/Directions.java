package com.mobilecityguide.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.models.POI;
import com.mobilecityguide.models.Point;
import com.mobilecityguide.models.Road;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.GPSController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;

public class Directions extends Activity implements LocationListener {
	
	private Road mRoad;
	private int step;
	private POI poi;
	private Location poiLocation;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		step = 1;
		poi = UserController.selectedItinerary.getPOIList().get(new Integer(step)); // retrieve this step POI
		poiLocation = new Location(LocationManager.GPS_PROVIDER);
		poiLocation.setLatitude(poi.getLatitude());
		poiLocation.setLongitude(poi.getLongitude());
		
		/* Get user's location */
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		Location userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

		/* Monitor position changes */
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		//String url = GPSController.getUrl(userLocation, poiLocation);
		//InputStream is = getConnection(url);
		//mRoad = GPSController.getRoute(is);
		
		setContentView(R.layout.directions);
		
		addDirections();
	}

	private void addDirections() {
		for (int i = 0; i < mRoad.mPoints.length-1; i++) {
			TextView container = new TextView(this);
			if (i == mRoad.mPoints.length-2) // if it's the last direction, no need to show distance
				container.setText(mRoad.mPoints[i].mName);
			else
				container.setText(mRoad.mPoints[i].mName+" "+mRoad.mPoints[i].mDescription);
			container.setId(i);
			container.setLayoutParams(new LinearLayout.LayoutParams(
		    		LinearLayout.LayoutParams.FILL_PARENT,
		    		LinearLayout.LayoutParams.WRAP_CONTENT));
			LinearLayout layout = (LinearLayout) findViewById(R.id.directions);
			layout.addView(container);
		}
	}

	private void moveToNextPoi() {
		this.step++;
		//this.previousPoi;
		this.poi = UserController.selectedItinerary.getPOIList().get(new Integer(this.step));
		this.poiLocation = new Location(LocationManager.GPS_PROVIDER);
		this.poiLocation.setLatitude(poi.getLatitude());
		this.poiLocation.setLongitude(poi.getLongitude());
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		System.out.println("Location changed");
		System.out.println(arg0.getLatitude());
		System.out.println(arg0.getLongitude());
		System.out.println("Distance from POI: "+arg0.distanceTo(poiLocation));
		
		/* if we're less than 50 meters away from the POI, show its informations */
		if (arg0.distanceTo(poiLocation) <= 50) {
			Intent intent = new Intent(this, PoiDetails.class);
			intent.putExtra("id", true);
			intent.putExtra("poi", poi.getId());
			startActivity(intent);
			
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		System.out.println("Provider disabled: "+arg0);
	}

	@Override
	public void onProviderEnabled(String arg0) {
		System.out.println("Provider enabled: "+arg0);
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		System.out.println("Status changed: "+arg0);
	}
	
	private InputStream getConnection(String url) {
		InputStream is = null;
		try {
			URLConnection conn = new URL(url).openConnection();
			is = conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.quit:
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
