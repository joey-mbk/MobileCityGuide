package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

public class MainMenu extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		setListeners();
	}

	private void setListeners() {
		View startButton = findViewById(R.id.start_button);
		startButton.setOnClickListener(this);
		View itineraryButton = findViewById(R.id.itinerary_button);
		itineraryButton.setOnClickListener(this);
		View settingsButton = findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(this);
		View disconnectButton = findViewById(R.id.disconnect_button);
		disconnectButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.start_button:
			intent = new Intent(this, Start.class);
			startActivity(intent);
			break;
		case R.id.itinerary_button:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
		case R.id.settings_button:
			intent = new Intent(this, Settings.class);
			startActivity(intent);
			break;
		case R.id.disconnect_button:
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			break;
		}
	}
}
