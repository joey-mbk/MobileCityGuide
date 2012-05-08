package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.R;

public class ItinerariesList extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itineraries_list);
		setListeners();
	}

	private void setListeners() {
		View addPoiButton = findViewById(R.id.create_itinerary);
		addPoiButton.setOnClickListener(this);
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
