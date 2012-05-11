package com.mobilecityguide.activity;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PoiDetails extends Activity implements OnClickListener {

	private boolean inItinerary;
	private boolean freewalk;
	private int step;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Retrieve the selected POI */
		Bundle extras = getIntent().getExtras(); // retrieve the variables from previous intent
		String poiName = null;
		int poiID = 0;
		POI poi = null;
		try {
			if (extras != null) {
				this.inItinerary = extras.getBoolean("itinerary");
				this.freewalk = extras.getBoolean("freewalk");
			}
			if (this.inItinerary) {
				this.step = extras.getInt("step");
				poiID = extras.getInt("poi");
				poi = POIController.getPOI(poiID);
			}
			else if (this.freewalk) {
				poiID = extras.getInt("poi");
				poi = POIController.getPOI(poiID);
			}
			else {
				poiName = extras.getString("poi"); // retrieve the name of the POI
				poi = POIController.getPOI(poiName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] languages = UserController.activeUser.getLanguage();
		String desc = null;
		for (String lang : languages) {
			desc = poi.getDescription(UserController.activeUser.getAge(), lang);
			if (desc != null)
				break;
		}
		
		/* if no suitable description was found, try to find anyone */
		if (desc == null) {
			for (String lang : languages) {
				desc = poi.getDescription("adult", lang);
				if (desc != null)
					break;
			}
		}
		
		/* Set the details in corresponding text fields */
		setContentView(R.layout.poi_details);
		((TextView) findViewById(R.id.description)).setText(desc);
		((TextView) findViewById(R.id.address)).setText(poi.getAddress());

		/* if we're coming from an itinerary, show a 'Next' button */
		if (this.inItinerary) {
			Button container = new Button(this);
			/* check if it's the last poi in the itinerary */
			if (UserController.selectedItinerary.getPOIList().size() == step)
				container.setText("Finish");
			else
				container.setText("Next");
			container.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			container.setOnClickListener(this);
			container.setBackgroundResource(R.drawable.buttonroundedcorners);
			LinearLayout layout = (LinearLayout) findViewById(R.id.info_list);
			layout.addView(container);
		}
		else if (this.freewalk) {
			Button container = new Button(this);
			container.setText("Close");
			container.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			container.setOnClickListener(this);
			container.setBackgroundResource(R.drawable.buttonroundedcorners);
			LinearLayout layout = (LinearLayout) findViewById(R.id.info_list);
			layout.addView(container);
		}
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

	@Override
	public void onClick(View arg0) {
		/* check if it's the last poi in the itinerary */
		Intent intent;
		if (this.inItinerary) {
			if (UserController.selectedItinerary.getPOIList().size() == step)
				intent = new Intent(this, ItinerariesList.class);
			else
				intent = new Intent(this, Directions.class); // get directions towards the next poi in the itinerary
			intent.putExtra("step", this.step+1);
			startActivity(intent);
		}
		else if (this.freewalk) {
			intent = new Intent(this, FreeWalk.class);
			startActivity(intent);
		}
	}
}
