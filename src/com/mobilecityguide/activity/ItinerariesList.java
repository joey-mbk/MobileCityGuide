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

public class ItinerariesList extends Activity implements OnClickListener, OnItemClickListener {

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

		options_f = null;
		selections_f = null;
		filters = null;
		filtersList = new ArrayList<String>(); 
		itinerariesList = null;
		itinerariesListView = null;
		adapter = null; 
		tempItinerariesList = null;

		context = this;

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
			itinerariesArrayList = UserController.getActiveUserItinerariesNames();
			itinerariesArrayList.addAll(ItineraryController.getPredefCityItinerariesTitles());
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
			intent = new Intent(this, CreateProfile.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	private void setListeners() {
		View createButton = findViewById(R.id.create_itinerary);
		createButton.setOnClickListener(this);

		View deleteButton2 = findViewById(R.id.delete_itinerary);
		deleteButton2.setOnClickListener(this);

		View chooseFiltersButton = findViewById(R.id.filtersbutton);
		chooseFiltersButton.setOnClickListener(this);

		itinerariesListView = (ListView)findViewById(R.id.list);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itinerariesList);
		itinerariesListView.setAdapter(adapter);
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
		case R.id.delete_itinerary:
			intent = new Intent(this, DeleteItinerary.class);
			startActivity(intent);
			break;
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
					if (selections_f[0])
						itinerariesIDList.addAll(UserController.activeUser.getUserItinerariesID());
					if(selections_f[1]){
						try {
							itinerariesIDList.addAll(ItineraryController.getPredefCityItinerariesID());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					for(Integer i : itinerariesIDList){
						try {
							System.out.println(ItineraryController.getItineraryCategoryName(ItineraryController.getItinerary(i)));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					for (int i = 2; i < options_f.length; i++) {
						if (!selections_f[i]) {
							try {
								System.out.println(CategoryController.getCategoryTitle(CategoryController.getCategory(options_f[i].toString()).getId()));
								ArrayList<Integer> itOfCatList = ItineraryController.getItineraryOfCategory(itinerariesIDList,CategoryController.getCategory(options_f[i].toString()));
								for(Integer id: itOfCatList){
									itinerariesIDList.remove(id);
								}
				
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
						adapter.notifyDataSetChanged();
					}

				}
				break;
			}
		}
	}

}
