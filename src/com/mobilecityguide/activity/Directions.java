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
import android.location.LocationManager;
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

public class Directions extends Activity {
	
	private Road mRoad;
	private int step;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		step = 0;
		
		/* Get user's location */
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		Location userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

		String url = GPSController.getUrl(userLocation, UserController.selectedItinerary.getPOIList().get(new Integer(step)));
		System.out.println(url);
		InputStream is = getConnection(url);
		mRoad = GPSController.getRoute(is);
		
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
