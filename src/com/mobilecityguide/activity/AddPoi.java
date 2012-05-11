package com.mobilecityguide.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

public class AddPoi extends Activity implements OnClickListener , OnItemClickListener {

	protected CharSequence[] options_f;
	protected boolean[] selections_f;
	AlertDialog.Builder filters = null;
	ArrayList<String> filtersList = new ArrayList<String>();
	ListView poiListView;
	ArrayAdapter<String> adapter;
	Context context;
	private String[]poiNamesList;

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

		// Retrieve all pois
		try {
			ArrayList<String> pois = POIController.getPOINamesOfCity();

			//Delete pois who are already present in itinerary
			ArrayList<String> itineraryPois = new ArrayList<String>();
			HashMap<Integer, POI> poiHashMap = UserController.selectedItinerary.getPOIList();
			for (Entry<Integer, POI> entry : poiHashMap.entrySet())
				itineraryPois.add(POIController.getPOIName(entry.getValue()).toString());
			pois.removeAll(itineraryPois);

			poiNamesList = new String[pois.size()];
			for (int i = 0; i < pois.size(); i++)
				poiNamesList[i] = pois.get(i);
		} catch (Exception e) {
			poiNamesList = null;
			e.printStackTrace();
		}

		setContentView(R.layout.add_poi);
		
		((TextView) findViewById(R.id.poi_title)).setText(UserController.city); // setting window title
		
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
		View chooseFiltersButton = findViewById(R.id.filtersbutton);
		chooseFiltersButton.setOnClickListener(this);

		poiListView = (ListView)findViewById(R.id.list);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,poiNamesList);
		poiListView.setAdapter(adapter);
		poiListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			UserController.selectedItinerary.addNextPOI(POIController.getPOI(poiNamesList[(int) id]));
			ItineraryController.saveItinerary(UserController.selectedItinerary);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		startActivity(new Intent(this, PoisList.class));
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.filtersbutton:
			AlertDialog.Builder filters = new AlertDialog.Builder(this);
			filters.setTitle("Select filter(s)");
			filters.setMultiChoiceItems(options_f, selections_f, new DialogSelectionClickHandler("filters"));
			filters.setPositiveButton("OK", new DialogButtonClickHandler("filters"));
			filters.show();
			break;
		}
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

}
