package com.mobilecityguide.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

public class PoisList extends Activity implements OnClickListener, OnItemClickListener {

	ArrayList<String> pois = new ArrayList<String>(); // list of pois chosen by the user
	private ArrayAdapter<String> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.pois_list);
		
		/* Set window titles */
		((TextView) findViewById(R.id.city_title)).setText(UserController.city);
		String itineraryTitle = ItineraryController.getItineraryTitle(UserController.selectedItinerary);
		((TextView) findViewById(R.id.itinerary_title)).setText(itineraryTitle);
		
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
			intent = new Intent(this, CreateProfile.class);
			startActivity(intent);
			return true;

		}
		return false;
	}

	private void setListeners() {
		View addPoiButton = findViewById(R.id.add_poi);
		addPoiButton.setOnClickListener(this);
		
		View delPoiButton = findViewById(R.id.delete_poiButton);
		delPoiButton.setOnClickListener(this);

		View startButton = findViewById(R.id.start);
		startButton.setOnClickListener(this);

		ListView poiListView = (ListView)findViewById(R.id.list);
		//Remplissage de la liste de nom des poi
		HashMap<Integer, POI> poiHashMap;
		try {
			poiHashMap = UserController.selectedItinerary.getPOIList();
			for (Entry<Integer, POI> entry : poiHashMap.entrySet())
				pois.add(entry.getKey()-1, POIController.getPOIName(entry.getValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pois);
		poiListView.setAdapter(adapter);
		poiListView.invalidateViews();
		poiListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		Intent intent = new Intent(this, PoiDetails.class);
		intent.putExtra("itinerary", false);
		intent.putExtra("freewalk", false);
		intent.putExtra("poi", pois.get((int) id));
		startActivity(intent);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_poi:
			startActivity(new Intent(this, AddPoi.class));
			break;
		case R.id.delete_poiButton:
			startActivity(new Intent(this, DeletePoi.class));
			break;
		case R.id.start:
			Intent intent = new Intent(this, Directions.class);
			intent.putExtra("step", 1);
			startActivity(intent);
			break;
		}
	}
}
