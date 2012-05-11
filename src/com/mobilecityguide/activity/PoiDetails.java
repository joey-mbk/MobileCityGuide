package com.mobilecityguide.activity;

import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PoiDetails extends Activity implements OnClickListener {
	
	private boolean inItinerary;
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
			if (extras != null)
				this.inItinerary = extras.getBoolean("itinerary");
				if (this.inItinerary) {
					this.step = extras.getInt("step");
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
	}

	@Override
	public void onClick(View arg0) {
		/* check if it's the last poi in the itinerary */
		Intent intent;
		if (UserController.selectedItinerary.getPOIList().size() == step)
			intent = new Intent(this, ItinerariesList.class);
		else
			intent = new Intent(this, Directions.class); // get directions towards the next poi in the itinerary
		intent.putExtra("step", this.step+1);
		startActivity(intent);
	}
}
