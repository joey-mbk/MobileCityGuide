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

public class CreateProfile extends Activity implements OnClickListener {

	protected CharSequence[] options_a = {"Kid", "Adult"};
	protected int selections_a;
	protected CharSequence[] options_l = {"Francais", "English", "Nederlands"};
	protected boolean[] selections_l =  new boolean[ options_l.length ];
	protected CharSequence[] options_i = {"Sport", "Cinema", "Opera", "Zoo"};
	protected boolean[] selections_i =  new boolean[ options_i.length ];

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_profile);
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
		}
		return false;
	}

	private void setListeners() {
		View saveButton = findViewById(R.id.save);
		saveButton.setOnClickListener(this);

		View chooseAgeButton = findViewById(R.id.ages);
		chooseAgeButton.setOnClickListener(this);

		View chooseLanguagesButton = findViewById(R.id.choose_languages);
		chooseLanguagesButton.setOnClickListener(this);

		View chooseInterestsButton = findViewById(R.id.choose_interests);
		chooseInterestsButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.save:
			intent = new Intent(this, Connect.class);
			startActivity(intent);
			break;
		case R.id.ages:
			AlertDialog.Builder ages = new AlertDialog.Builder(this);
			ages.setTitle("Choose your age");
			ages.setSingleChoiceItems(options_a, selections_a, new DialogSingleSelectionClickHandler());
			ages.setPositiveButton("OK", new DialogButtonClickHandler());
			ages.show();
			break;
		case R.id.choose_languages:
			AlertDialog.Builder languages = new AlertDialog.Builder(this);
			languages.setTitle("Choose your language(s)");
			languages.setMultiChoiceItems(options_l, selections_l, new DialogSelectionClickHandler());
			languages.setPositiveButton("OK", new DialogButtonClickHandler());
			languages.show();
			break;
		case R.id.choose_interests:
			AlertDialog.Builder interests = new AlertDialog.Builder(this);
			interests.setTitle("Choose your centre(s) of interest");
			interests.setMultiChoiceItems(options_i, selections_i, new DialogSelectionClickHandler());
			interests.setPositiveButton("OK", new DialogButtonClickHandler());
			interests.show();
			break;
		}
	}

	public class DialogSelectionClickHandler implements DialogInterface.OnMultiChoiceClickListener {
		public void onClick(DialogInterface dialog, int clicked, boolean selected) {
			// TO DO
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
