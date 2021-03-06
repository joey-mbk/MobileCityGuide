package com.mobilecityguide.activity;

import java.util.ArrayList;

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
import com.mobilecityguide.controllers.UserController;

public class DeleteItinerary extends Activity implements OnClickListener, OnItemClickListener {

	protected CharSequence[] options_f;
	protected boolean[] selections_f;
	AlertDialog.Builder filters = null;
	ArrayList<String> filtersList = new ArrayList<String>(); // list of filters chosen by the user
	private String[]itinerariesList;
	ListView itinerariesListView;
	ArrayAdapter<String> adapter; 
	ArrayList<String> tempItinerariesList;
	Context context;

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

		/* Retrieve itineraries and fill the list */
		ArrayList<String> itinerariesArrayList;
		try {
			itinerariesArrayList = UserController.getActiveUserItinerariesNames();
			itinerariesArrayList.addAll(ItineraryController.getPredefCityItinerariesTitles());
			itinerariesList = new String[itinerariesArrayList.size()];
			itinerariesArrayList.toArray(itinerariesList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while retrieving itineraries.");
		}

		setContentView(R.layout.delete_itinerary);

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
			intent = new Intent(this, CreateProfile.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	private void setListeners() {

		View chooseFiltersButton = findViewById(R.id.filtersbutton);
		chooseFiltersButton.setOnClickListener(this);

		itinerariesListView = (ListView)findViewById(R.id.list);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itinerariesList);
		itinerariesListView.setAdapter(adapter);
		itinerariesListView.setOnItemClickListener(this);
	}


	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			ItineraryController.getCityItinerariesTitles();
			UserController.delUserItinerary((ItineraryController.getItinerary(itinerariesList[(int) id])));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while itinerary delete");
		}
		Intent intent = new Intent(this, ItinerariesList.class);
		startActivity(intent);
	}

	public void onClick(View v) {
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
					ArrayList<Integer> itinerariesIDList = new ArrayList<Integer>();
					itinerariesIDList.addAll(UserController.activeUser.getUserItinerariesID());
					
					for (int i = 0; i < options_f.length; i++) {
						if (!selections_f[i]) {
							try {
								for(Integer id: ItineraryController.getItineraryOfCategory(itinerariesIDList,CategoryController.getCategory(options_f[i].toString())))
									itinerariesIDList.remove(id);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}	
					}
					ArrayList<String> itinerariesNamesList= ItineraryController.getItinerariesTitles(itinerariesIDList);
					if (itinerariesNamesList.isEmpty()) {
						error = new AlertDialog.Builder(context);
						error.setMessage("No itinerary matches to your request");
						error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
						error.show();
					}
					else{
						itinerariesList = new String[itinerariesNamesList.size()];
						itinerariesNamesList.toArray(itinerariesList);
						adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,itinerariesList);
						itinerariesListView.setAdapter(adapter);
					}

				}
				break;
			}
		}
	}

}
