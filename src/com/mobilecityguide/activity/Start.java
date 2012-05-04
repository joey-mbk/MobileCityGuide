package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.R;

public class Start extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_menu);
		setListeners();
	}

	private void setListeners() {
		View freeWalkButton = findViewById(R.id.free_walk_button);
		freeWalkButton.setOnClickListener(this);
		View guideTourButton = findViewById(R.id.guide_tour_button);
		guideTourButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.free_walk_button:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
		case R.id.guide_tour_button:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
		}
	}
}
