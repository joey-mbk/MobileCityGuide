package com.mobilecityguide.activity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.datamappers.MyOverLay;
import com.mobilecityguide.models.POI;

public class FreeWalk extends MapActivity{

	/** Called when the activity is first created. */ 


	MapView mapView; 
	MapController mapController;
	GeoPoint points; 
	int color = Color.GREEN;

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
				paint.setColor(color);
				paint.setStrokeWidth(10);
				canvas.drawPoint((float) point.x, (float) point.y, paint);
				/*---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(
					getResources(), R.drawable.star);            
			canvas.drawBitmap(bmp, point.x, point.y-50, null);        
			return true;*/
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

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		MapController mc = mapView.getController(); 
		switch (keyCode) 
		{
		case KeyEvent.KEYCODE_3:
			mc.zoomIn();
			break;
		case KeyEvent.KEYCODE_1:
			mc.zoomOut();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}    



	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.free_map);

		mapView = (MapView) findViewById(R.id.myMapView1);


		mapController = mapView.getController();

		//city
		String city = "Louvain-La-Neuve"; // ˆ enlever
		UserController.setCity(city);

		if(UserController.getCity()!=""){

			POI[] poi = null;
			try {

				poi = POIController.getPOIOfCity();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<Overlay> listOfOverlays = null;
			//color=Color.RED;
			//MapOverlay userOverlay = new MapOverlay(currentUserLocation());
			//listOfOverlays = mapView.getOverlays();
			//listOfOverlays.add(userOverlay);
			//color=Color.GREEN;
			
			for (POI poi2 : poi) {

				points = new GeoPoint(
						(int) (poi2.getLatitude() * 1E6), 
						(int) (poi2.getLongitude() * 1E6));

				MapOverlay mapOverlay = new MapOverlay(points);
				listOfOverlays = mapView.getOverlays();
				listOfOverlays.add(mapOverlay);

			}
		}

		mapController.animateTo(points); //user normalement !!!!!!!!
		mapController.setZoom(17); 


		mapView.setSatellite(true);
		mapView.displayZoomControls(true);
	}

	public GeoPoint currentUserLocation (){
		
		GeoPoint points = null;

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

		double fromLat = lastKnownLocation.getLatitude();
		double fromLon = lastKnownLocation.getLongitude();
		
		points = new GeoPoint((int)fromLat, (int)fromLon);
		
		return points;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
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
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

}



