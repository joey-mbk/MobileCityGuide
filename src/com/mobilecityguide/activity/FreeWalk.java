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
import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.datamappers.MyOverLay;

public class FreeWalk extends MapActivity{

	/** Called when the activity is first created. */ 


	MapView mapView; 
	MapController mc;
	GeoPoint p, q;

	class MapOverlay extends com.google.android.maps.Overlay
	{
		@Override
		public boolean draw(Canvas canvas, MapView mapView, 
				boolean shadow, long when) 
		{
			super.draw(canvas, mapView, shadow);                   

			//---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);

			//---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(
					getResources(), R.drawable.star);            
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
			return true;
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


		mc = mapView.getController();
		String coordinates[] = {"1.352566007", "103.78921587"};
		double lat = 50.670558;
		double lng = 4.616135;
		
		double dest_lat = 50.667969; // the testing destination 
		double dest_long = 4.591487;

		/* Get user's location */
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));

		//double fromLat = lastKnownLocation.getLatitude();
		//double fromLon = lastKnownLocation.getLongitude();

		p = new GeoPoint(
				(int) (lat * 1E6), 
				(int) (lng * 1E6));
		q = new GeoPoint(
				(int) (dest_lat * 1E6), 
				(int) (dest_long * 1E6));

        
		mc.animateTo(p);
		mc.animateTo(q);
		
		
		mc.setZoom(17); 

		//---Add a location marker---
		MapOverlay mapOverlay = new MapOverlay();
		
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(mapOverlay); 

		
		//mapView.getOverlays().add(new MyOverLay(gp1,gp2,3,color)); 

		mapView.setTraffic(true);
		//mapView.setSatellite(true);
		mapView.setClickable(true);

		mapView.invalidate();


	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}



