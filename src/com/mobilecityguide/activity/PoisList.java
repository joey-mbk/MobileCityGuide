package com.mobilecityguide.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	protected CharSequence[] options;
	protected boolean[] selections;
	
	ArrayList<String> pois = new ArrayList<String>(); // list of pois chosen by the user
	ArrayList<String> poiTemp = new ArrayList<String>(); // buffer list of languages chosen by the user
	
	private ArrayAdapter<String> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Retrieve all pois */
		try {
			ArrayList<String> pois = POIController.getPOINamesOfCity();
			
			/* Delete pois who are already present in itinerary */
			ArrayList<String> itineraryPois = new ArrayList<String>();
			HashMap<Integer, POI> poiHashMap = UserController.selectedItinerary.getPOIList();
			for (Entry<Integer, POI> entry : poiHashMap.entrySet())
				itineraryPois.add(POIController.getPOIName(entry.getValue()).toString());
			pois.removeAll(itineraryPois);
			
			options = new CharSequence[pois.size()];
			for (int i = 0; i < pois.size(); i++)
				options[i] = pois.get(i);
		} catch (Exception e) {
			options = null;
			e.printStackTrace();
		}
		selections = new boolean[ options.length ];
		
		setContentView(R.layout.pois_list);
		
		/* Set window titles */
		((TextView) findViewById(R.id.city_title)).setText(UserController.city);
		String[] languages = UserController.activeUser.getLanguage();
		String itineraryTitle = null;
		for (String lang : languages) {
			itineraryTitle = UserController.selectedItinerary.getTitle(lang);
			if (itineraryTitle != null)
				break;
		}
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
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			return true;

		}
		return false;
	}

	private void setListeners() {
		View addPoiButton = findViewById(R.id.add_poi);
		addPoiButton.setOnClickListener(this);

		View startButton = findViewById(R.id.start);
		startButton.setOnClickListener(this);

		ListView poiListView = (ListView)findViewById(R.id.list);
		//Remplissage de la liste de nom des poi
		HashMap<Integer, POI> poiHashMap;
		try {
			poiHashMap = UserController.selectedItinerary.getPOIList();
			for (Entry<Integer, POI> entry : poiHashMap.entrySet())
				pois.add(entry.getKey(), POIController.getPOIName(entry.getValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pois);
		poiListView.setAdapter(adapter);
		poiListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		Intent intent = new Intent(this, PoiDetails.class);
		intent.putExtra("poi", pois.get((int) id));
		startActivity(intent);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.add_poi:
			AlertDialog.Builder addPoi = new AlertDialog.Builder(this);
			addPoi.setTitle(R.string.add_poi_title);
			addPoi.setMultiChoiceItems(options, selections, new DialogSelectionClickHandler("add_poi"));
			addPoi.setPositiveButton("OK", new DialogButtonClickHandler("add_poi"));
			addPoi.show();
			break;
		case R.id.start:
			startActivity(new Intent(this, Directions.class));
			break;
		}
	}
	
	public class DialogSelectionClickHandler implements DialogInterface.OnMultiChoiceClickListener {
		
		private String window;
		
		public DialogSelectionClickHandler(String window) {
			this.window = window;
		}
		
		public void onClick(DialogInterface dialog, int clicked, boolean selected) {
			if (window.equals("add_poi"))
				if (selected)
					poiTemp.add(options[clicked].toString());
				else
					poiTemp.remove(options[clicked].toString());
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
					if (window.equals("add_poi")) {
						pois.addAll(poiTemp); // if the user had previously selected pois, pois is outdated so we change it
						try {
							UserController.selectedItinerary.clear(); // reset the list of POIs of this itinerary
							for (int i = 0; i < pois.size(); i++)
								UserController.selectedItinerary.addPOI(i+1, POIController.getPOI(pois.get(i)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						poiTemp.clear(); // we flush the buffer, in case of future use
						adapter.notifyDataSetChanged(); // refresh the list of POIs
						//ItineraryController.saveItinerary(UserController.selectedItinerary); // Comment mettre à jour l'itinéraire ?
					}
				break;
			}
		}
	}
}
