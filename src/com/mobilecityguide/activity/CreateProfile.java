package com.mobilecityguide.activity;

import java.util.ArrayList;

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
import android.widget.EditText;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.Category;

public class CreateProfile extends Activity implements OnClickListener {

	protected CharSequence[] options_a = {"Adult", "Kid"};
	protected int selections_a;
	protected CharSequence[] options_l;
	protected boolean[] selections_l;
	protected CharSequence[] options_i;
	protected boolean[] selections_i;
	ArrayList<String> lang = new ArrayList<String>(); // list of languages chosen by the user
	ArrayList<String> langTemp = new ArrayList<String>(); // buffer list of languages chosen by the user
	ArrayList<Category> cat = new ArrayList<Category>(); // list of interests chosen by the user

	/* Error dialog */
	AlertDialog.Builder error;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			/* Retrieve all languages */
			ArrayList<String> lang = UserController.getAllLanguages();
			options_l = new CharSequence[lang.size()];
			for (int i = 0; i < lang.size(); i++)
				options_l[i] = lang.get(i);
		} catch (Exception e) {
			options_l = null;
			e.printStackTrace();
		}
		selections_l = new boolean[ options_l.length ];

		try {
			/* Retrieve all category titles */
			ArrayList<String> titles = CategoryController.getAllCategoriesTitles();
			options_i = new CharSequence[titles.size()];
			for (int i = 0; i < titles.size(); i++)
				options_i[i] = titles.get(i);
		} catch (Exception e) {
			options_i = null;
			e.printStackTrace();
		}
		selections_i = new boolean[ options_i.length ];

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
			String userName = ((EditText)findViewById(R.id.username)).getText().toString();
			/* if specified username is already used, alert the user */
			if (UserController.isUserNameAlreadyUsed(userName)) {
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.create_profile_error_alreadyused_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			/* check if at least one language was selected */
			else if (lang.isEmpty()) {
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.create_profile_error_nolang_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			/* check if at least one interest was selected */
			else if (cat.isEmpty()) {
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.create_profile_error_nointerest_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			/* check user name formatting */
			else if (userName == null || userName.equals("") || !userName.matches("[a-zA-Z0-9]+")) {
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.create_profile_error_usernameformat_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			/* if everything check was ok, add the user */
			else {
				UserController.addNewUser(userName, options_a[selections_a].toString().toLowerCase(), lang.toArray(new String[lang.size()]), cat);
				intent = new Intent(this, Connect.class);
				startActivity(intent);
			}
			break;
		case R.id.ages:
			AlertDialog.Builder age = new AlertDialog.Builder(this);
			age.setTitle(R.string.create_profile_age_title);
			age.setSingleChoiceItems(options_a, selections_a, new DialogSingleSelectionClickHandler());
			age.setPositiveButton("OK", new DialogButtonClickHandler("age"));
			age.show();
			break;
		case R.id.choose_languages:
			AlertDialog.Builder languages = new AlertDialog.Builder(this);
			languages.setTitle(R.string.create_profile_languages_title);
			languages.setMultiChoiceItems(options_l, selections_l, new DialogSelectionClickHandler("languages"));
			languages.setPositiveButton("OK", new DialogButtonClickHandler("languages"));
			languages.show();
			break;
		case R.id.choose_interests:
			AlertDialog.Builder interests = new AlertDialog.Builder(this);
			interests.setTitle(R.string.create_profile_interests_title);
			interests.setMultiChoiceItems(options_i, selections_i, new DialogSelectionClickHandler("interests"));
			interests.setPositiveButton("OK", new DialogButtonClickHandler("interests"));
			interests.show();
			break;
		}
	}

	public class DialogSelectionClickHandler implements DialogInterface.OnMultiChoiceClickListener {

		private String window;

		public DialogSelectionClickHandler(String window) {
			this.window = window;
		}

		public void onClick(DialogInterface dialog, int clicked, boolean selected) {
			if (window.equals("languages"))
				if (selected)
					langTemp.add(options_l[clicked].toString());
				else
					langTemp.remove(options_l[clicked].toString());
			if (window.equals("interests"))
				selections_i[clicked] = selected;
		}
	}

	public class DialogSingleSelectionClickHandler implements android.content.DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			selections_a = which;
		}
	}

	public class DialogButtonClickHandler implements DialogInterface.OnClickListener {

		private String window;

		public DialogButtonClickHandler(String window) {
			this.window = window;
		}

		public void onClick(DialogInterface dialog, int clicked) {
			switch(clicked)	{
			case DialogInterface.BUTTON_POSITIVE:
				if (window.equals("languages")) {
					lang.addAll(langTemp); // if the user had previously selected languages, lang is outdated so we change it
					langTemp.clear(); // we flush the buffer, in case of future use
				}

				if (window.equals("interests")) {
					Category category = null;
					cat.clear(); // if the user had previously selected interests, cat is not empty so we clear it
					for (int i = 0; i < options_i.length; i++) {
						if (selections_i[i]) {
							category = CategoryController.getCategory(options_i[i].toString());
							cat.add(category);
						}	
					}
				}
				break;
			}
		}
	}
}
