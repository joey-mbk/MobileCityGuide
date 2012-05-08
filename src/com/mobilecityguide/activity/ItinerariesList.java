package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

public class ItinerariesList extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itineraries_list);
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
