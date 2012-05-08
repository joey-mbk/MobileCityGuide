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

public class Start extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_menu);
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
		View freeWalkButton = findViewById(R.id.free_walk_button);
		freeWalkButton.setOnClickListener(this);
		View guideTourButton = findViewById(R.id.guide_tour_button);
		guideTourButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.free_walk_button:
			intent = new Intent(this, FreeWalk.class);
			startActivity(intent);
			break;
		case R.id.guide_tour_button:
			intent = new Intent(this, ItinerariesList.class);
			startActivity(intent);
			break;
		}
	}
}
