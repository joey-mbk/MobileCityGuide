package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

public class Settings extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
        setListeners();
	}
	private void setListeners() {
        View itineraryButton = findViewById(R.id.itinerary_bouton);
        itineraryButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.itinerary_bouton:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
	}
	}
}
