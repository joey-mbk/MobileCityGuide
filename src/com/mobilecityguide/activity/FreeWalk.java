package com.mobilecityguide.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.models.POI;

public class FreeWalk extends Activity implements OnClickListener , OnItemClickListener, LocationListener {

	protected CharSequence[] options_f;
	protected boolean[] selections_f;
	AlertDialog.Builder filters = null;
	ArrayList<String> filtersList = new ArrayList<String>();
	ListView poiListView;
	ArrayAdapter<String> adapter;
	Context context;
	private String[]poiNamesList;
	GeoPoint points=null;
	MapView mapView;

	/* Error dialog */
	AlertDialog.Builder error;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;

		/* Fill the filter window menu */
		try {
			ArrayList<String> titles = CategoryController.getAllCategoriesTitles();
			options_f = new CharSequence[titles.size()];
			for (int i = 0; i < titles.size(); i++)
				options_f[i] = titles.get(i);
		} catch (Exception e) {
			options_f = null;
			e.printStackTrace();
		}
		selections_f = new boolean[ options_f.length ];

		//Remplissage de la liste de nom des poi
		POI[] poiList =null;
		try {
			poiList = POIController.getPOIOfCity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		poiNamesList = new String[poiList.length];
		for(int i=0; i<poiList.length;i++)
			poiNamesList[i]=POIController.getPOIName(poiList[i]);

		List<Overlay> listOfOverlays = null;
		for (POI poi2 : poiList) {

			points = new GeoPoint(
					(int) (poi2.getLatitude() * 1E6), 
					(int) (poi2.getLongitude() * 1E6));

			MapOverlay mapOverlay = new MapOverlay(points);
			 listOfOverlays = mapView.getOverlays();
			listOfOverlays.add(mapOverlay);

		}

		setContentView(R.layout.free_walk);
		//((TextView) findViewById(R.id.city_title)).setText(UserController.city); // setting window title
		setListeners();
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

	private void setListeners() {
		/*View guideTourButton = findViewById(R.id.start);
		guideTourButton.setOnClickListener(this);


		View chooseFiltersButton = findViewById(R.id.filtersbutton);
		chooseFiltersButton.setOnClickListener(this);

		poiListView = (ListView)findViewById(R.id.list);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,poiNamesList);
		poiListView.setAdapter(adapter);
		poiListView.setOnItemClickListener(this);*/
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		Intent intent = new Intent(this, PoiDetails.class);
		intent.putExtra("poi", poiNamesList[(int) id]);
		startActivity(intent);
	}

	public void onClick(View v) {
		Intent intent;
		/*switch (v.getId()) {
		/*case R.id.start:
			//TODO start freewalk
			break;
		case R.id.filtersbutton:
			AlertDialog.Builder filters = new AlertDialog.Builder(this);
			filters.setTitle("Select filter(s)");
			filters.setMultiChoiceItems(options_f, selections_f, new DialogSelectionClickHandler("filters"));
			filters.setPositiveButton("OK", new DialogButtonClickHandler("filters"));
			filters.show();
			break;
		}*/
	}
	public class DialogSelectionClickHandler implements DialogInterface.OnMultiChoiceClickListener {

		private String window;

		public DialogSelectionClickHandler(String window) {
			this.window = window;
		}

		public void onClick(DialogInterface dialog, int clicked, boolean selected) {
			if (window.equals("filters"))
				selections_f[clicked] = selected;
		}
	}

	public class DialogButtonClickHandler implements DialogInterface.OnClickListener {

		private String window;

		public DialogButtonClickHandler(String window) {
			this.window = window;
		}

		public void onClick(DialogInterface dialog, int clicked) {
			switch(clicked)	{
			case DialogInterface.BUTTON_POSITIVE:

				if (window.equals("filters")) {
					ArrayList<String> poiArrayList = new ArrayList<String>();
					for (int i = 0; i < options_f.length; i++) {
						if (selections_f[i]) {
							try {
								for(String poi: POIController.getPOINamesofCategory(((CategoryController.getCategory(options_f[i].toString()))))){
									if(!poiArrayList.contains(poi))
										poiArrayList.add(poi);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}	
					}
					if (poiArrayList.isEmpty()) {
						error = new AlertDialog.Builder(context);
						error.setMessage("No poi matches to your request");
						error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
						error.show();
					}
					else{
						poiNamesList = new String[poiArrayList.size()];
						poiArrayList.toArray(poiNamesList);
						adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,poiNamesList);
						poiListView.setAdapter(adapter);
					}

				}
				break;
			}
		}
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




	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}

