package com.mobilecityguide.activity;

import java.util.ArrayList;

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
import com.mobilecityguide.activity.CreateProfile.DialogButtonClickHandler;
import com.mobilecityguide.activity.CreateProfile.DialogSelectionClickHandler;
import com.mobilecityguide.activity.CreateProfile.DialogSingleSelectionClickHandler;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.Category;

public class ItinerariesList extends Activity implements OnClickListener, OnItemClickListener {

	protected CharSequence[] options_f;
	protected boolean[] selections_f;
	AlertDialog.Builder filters = null;
	ArrayList<String> filtersList = new ArrayList<String>(); // list of filters chosen by the user
	private String[]itinerariesList;

	ArrayList<String> tempItinerariesArrayList = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Fill the filter window menu */
		try {
			ArrayList<String> titles = CategoryController.getAllCategoriesTitles();
			options_f = new CharSequence[titles.size()+2];
			options_f[0]= "Personnal itineraries";
			options_f[1]= "Predefined itineraries";
			for (int i = 2; i < titles.size()+2; i++)
				options_f[i] = titles.get(i-2);
		} catch (Exception e) {
			options_f = null;
			e.printStackTrace();
		}
		selections_f = new boolean[ options_f.length ];

		/* Retrieve itineraries and fill the list */
		ArrayList<String> itinerariesArrayList;
		try {
			itinerariesArrayList = ItineraryController.getCityItinerariesTitles();
			itinerariesList = new String[itinerariesArrayList.size()];
			itinerariesArrayList.toArray(itinerariesList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while retrieving itineraries.");
		}

		setContentView(R.layout.itineraries_list);
		
		((TextView) findViewById(R.id.city_title)).setText(UserController.city); // setting window title
		
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

		View chooseFiltersButton = findViewById(R.id.filtersbutton);
		chooseFiltersButton.setOnClickListener(this);

		ListView itinerariesListView = (ListView)findViewById(R.id.list);
		itinerariesListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itinerariesList));
		itinerariesListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			UserController.selectedItinerary = ItineraryController.getItinerary(itinerariesList[(int) id]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while setting the selected itinerary");
		}
		Intent intent = new Intent(this, PoisList.class);
		startActivity(intent);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.create_itinerary:
			intent = new Intent(this, CreateItinerary.class);
			startActivity(intent);
			break;
		case R.id.filtersbutton:
			AlertDialog.Builder interests = new AlertDialog.Builder(this);
			interests.setTitle("Select filter(s)");
			interests.setMultiChoiceItems(options_f, selections_f, new DialogSelectionClickHandler("filters"));
			interests.setPositiveButton("OK", new DialogButtonClickHandler("filters"));
			interests.show();
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
					ArrayList<Integer> itinerariesIDList = new ArrayList<Integer>();
					if (selections_f[0])
						itinerariesIDList.addAll(UserController.activeUser.getUserItinerariesID());
					if(selections_f[1]){
						try {
							itinerariesIDList.addAll(ItineraryController.getPredefCityItinerariesID());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					for (int i = 2; i < options_f.length; i++) {
						if (!selections_f[i]) {
							try {
								itinerariesIDList.removeAll(ItineraryController.getItineraryOfCategory(itinerariesIDList,CategoryController.getCategory(options_f[i].toString())));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}	
					}
					ArrayList<String> itinerariesNamesList= ItineraryController.getItinerariesTitles(itinerariesIDList);
					itinerariesNamesList.toArray(itinerariesList);
				}
				break;
			}
		}
	}

}
