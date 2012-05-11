package com.mobilecityguide.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.GPSController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;
import com.mobilecityguide.models.Road;

public class FreeWalk extends Activity implements LocationListener {

	private Road mRoad;
	private int step;
	private POI[] poi;
	private Location poiLocation;
	private MapView mapView;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//step = 1;
		try {
			poi = POIController.getPOIOfCity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // retrieve this step POI
		
		/* Get user's location */
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		Location userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

		/* Monitor position changes */
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


		List<Overlay> listOfOverlays = null;
		GeoPoint points = null;

		for (POI currentPoi : poi) {
			
			poiLocation = new Location(LocationManager.GPS_PROVIDER);
			poiLocation.setLatitude(currentPoi.getLatitude());
			poiLocation.setLongitude(currentPoi.getLongitude());
			
			String url = GPSController.getUrl(userLocation, currentPoi);
			InputStream is = getConnection(url);
			mRoad = GPSController.getRoute(is);

			points = new GeoPoint(
					(int) (currentPoi.getLatitude() * 1E6), 
					(int) (currentPoi.getLongitude() * 1E6));

			MapOverlay mapOverlay = new MapOverlay(points);
			listOfOverlays = mapView.getOverlays();
			listOfOverlays.add(mapOverlay);

		}

		MapController mapController = mapView.getController();
		mapController.animateTo(points); 
		mapController.setZoom(17); 

		mapView.setSatellite(true);
		mapView.displayZoomControls(true);

		setContentView(R.layout.free_walk);
		//addDirections();
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
/*
	private void moveToNextPoi() {
		this.step++;
		this.previousPoi;
		this.poi = UserController.selectedItinerary.getPOIList().get(new Integer(this.step));
		this.poiLocation = new Location(LocationManager.GPS_PROVIDER);
		this.poiLocation.setLatitude(poi.getLatitude());
		this.poiLocation.setLongitude(poi.getLongitude());
	}
*/
	@Override
	public void onLocationChanged(Location arg0) {
		System.out.println("Location changed");
		System.out.println(arg0.getLatitude());
		System.out.println(arg0.getLongitude());
		System.out.println("Distance from POI: "+arg0.distanceTo(poiLocation));

		/* if we're less than 50 meters away from the POI, show its informations */
		for (POI poi2 : poi) {
			
			if (arg0.distanceTo(poiLocation) <= 50) {
				Intent intent = new Intent(this, PoiDetails.class);
				intent.putExtra("id", true);
				intent.putExtra("poi", poi2.getId());
				startActivity(intent);

			}
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

	public class MapOverlay extends Overlay {
		private GeoPoint data;  

		public MapOverlay( GeoPoint item) {
			data = item;
		}

		/* (non-Javadoc)
		 * @see com.google.android.maps.Overlay#draw(android.graphics.Canvas, com.google.android.maps.MapView, boolean, long)
		 */

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,long when) {
			Projection projection = mapView.getProjection();
			if (shadow == false) {
				Paint paint = new Paint();
				paint.setAntiAlias(true);
				Point point = new Point();
				projection.toPixels(data, point);
				paint.setColor(Color.GREEN);
				paint.setStrokeWidth(10);
				canvas.drawPoint((float) point.x, (float) point.y, paint);
			}
			return super.draw(canvas, mapView, shadow, when);
		}

		/* (non-Javadoc)
		 * @see com.google.android.maps.Overlay#draw(android.graphics.Canvas, com.google.android.maps.MapView, boolean)
		 */
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {

			super.draw(canvas, mapView, shadow);
		}       

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) 
		{  
			//---when user lifts his finger---
			if (event.getAction() == 1) {                
				GeoPoint p = mapView.getProjection().fromPixels(
						(int) event.getX(),
						(int) event.getY());

				Geocoder geoCoder = new Geocoder(
						getBaseContext(), Locale.getDefault());
				try {
					List<Address> addresses = geoCoder.getFromLocation(
							p.getLatitudeE6()  / 1E6, 
							p.getLongitudeE6() / 1E6, 1);

					String add = "";
					if (addresses.size() > 0) 
					{
						for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
								i++)
							add += addresses.get(0).getAddressLine(i) + "\n";
					}

					Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
				}
				catch (IOException e) {                
					e.printStackTrace();
				}   
				return true;
			}
			else                
				return false;
		}        
	} 

}