package com.mobilecityguide.activity;

import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PoiDetails extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Retrieve the selected POI */
		Bundle extras = getIntent().getExtras(); // retrieve the variables from previous intent
		String poiName = null;
		POI poi = null;
		try {
			if (extras != null)
				poiName = extras.getString("poi"); // retrieve the name of the POI
				poi = POIController.getPOI(poiName);
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
	}
}
