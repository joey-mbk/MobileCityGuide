package com.mobilecityguide.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

public class CreateItinerary extends Activity implements OnClickListener {

	protected CharSequence[] options_t = {"Sport", "Culture", "Musique"};
	protected int selections_t;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_itinerary);
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
		View chooseThemeButton = findViewById(R.id.themes);
		chooseThemeButton.setOnClickListener(this);

		View saveButton = findViewById(R.id.add_pois);
		saveButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.themes:
			AlertDialog.Builder ages = new AlertDialog.Builder(this);
			ages.setTitle("Choose the theme of the itinerary");
			ages.setSingleChoiceItems(options_t, selections_t, new DialogSingleSelectionClickHandler());
			ages.setPositiveButton("OK", new DialogButtonClickHandler());
			ages.show();
			break;
		case R.id.add_pois:
			intent = new Intent(this, PoisList.class);
			startActivity(intent);
			break;
		}
	}

	public class DialogSingleSelectionClickHandler implements android.content.DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			// TO DO
		}
	}

	public class DialogButtonClickHandler implements DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int clicked) {
			switch(clicked)	{
			case DialogInterface.BUTTON_POSITIVE:
				// TO DO
				break;
			}
		}
	}
}
