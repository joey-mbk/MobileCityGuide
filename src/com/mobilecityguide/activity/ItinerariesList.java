package com.mobilecityguide.activity;

import java.util.ArrayList;

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

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;

public class ItinerariesList extends Activity implements OnClickListener, OnItemClickListener {

	private String[]itinerariesList;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itineraries_list);
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
		View addPoiButton = findViewById(R.id.create_itinerary);
		addPoiButton.setOnClickListener(this);
		
		ListView itinerariesListView = (ListView)findViewById(R.id.list);
		//Remplissage de la liste de nom des itinéraires
		ArrayList<String> itinerariesArrayList;
		try {
			itinerariesArrayList = ItineraryController.getCityItinerariesTitles();
			itinerariesList = new String[itinerariesArrayList.size()];
			itinerariesArrayList.toArray(itinerariesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		itinerariesListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itinerariesList));
		itinerariesListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			//TODO Démarrer une visite
			System.out.println((itinerariesList[(int) id]));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		startActivity(new Intent(this, PoisList.class));
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.create_itinerary:
			intent = new Intent(this, CreateItinerary.class);
			startActivity(intent);
			break;
		}
	}
}
