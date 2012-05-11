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
import com.google.android.maps.MapActivity;
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

public class FreeWalk extends MapActivity implements LocationListener {

	private Road mRoad;
	private int step;
	private POI[] poi;
	private Location poiLocation;
	private MapView mapView;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.free_walk);
		
mapView = (MapView) findViewById(R.id.map);
		
		try {
			poi = POIController.getPOIOfCity();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Overlay> listOfOverlays = null;
		GeoPoint point = null;

		for (POI currentPoi : poi) {
			point = new GeoPoint((int) (currentPoi.getLatitude()*1E6), (int) (currentPoi.getLongitude()*1E6));
			MapOverlay mapOverlay = new MapOverlay(point);
			listOfOverlays = mapView.getOverlays();
			listOfOverlays.add(mapOverlay);

		}

		MapController mapController = mapView.getController();
		mapController.animateTo(point); 
		mapController.setZoom(17); 

		mapView.setSatellite(true);
		mapView.displayZoomControls(true);

		/* Get user's location */
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		Location userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

		/* Monitor position changes */
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		System.out.println("Location changed");
		/* if we're less than 20 meters away from a POI, show its informations */
		for (POI aPoi : poi) {
			poiLocation = new Location(LocationManager.GPS_PROVIDER);
			poiLocation.setLatitude(aPoi.getLatitude());
			poiLocation.setLongitude(aPoi.getLongitude());
			
			if (arg0.distanceTo(poiLocation) <= 20) {
				Intent intent = new Intent(this, PoiDetails.class);
				intent.putExtra("freewalk", true);
				intent.putExtra("poi", aPoi.getId());
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
		case R.id.change_user:
			intent = new Intent(this, Connect.class);
			startActivity(intent);
			return true;
		case R.id.change_city:
			intent = new Intent(this, CitiesList.class);
			startActivity(intent);
			return true;
		case R.id.edit_profile:
			intent = new Intent(this, CreateProfile.class);
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	} 

}