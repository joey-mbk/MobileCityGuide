package com.mobilecityguide.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mobilecityguide.R;

public class CreateProfile extends Activity implements OnClickListener {

	protected CharSequence[] options_l = {"Français", "English", "Nederlands"};
	protected boolean[] selections_l =  new boolean[ options_l.length ];
	protected CharSequence[] options_i = {"Sport", "Cinema", "Opera", "Zoo"};
	protected boolean[] selections_i =  new boolean[ options_i.length ];

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_profile);
		setListeners();
		setSpinners();
	}

	private void setSpinners() {
	    Spinner s = (Spinner) findViewById(R.id.ages);
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ages, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s.setAdapter(adapter);
	}

	private void setListeners() {
		View saveButton = findViewById(R.id.save);
		saveButton.setOnClickListener(this);

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
