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
	ArrayList<Category> cat = new ArrayList<Category>(); // list of interests chosen by the user
	
	/* Alert dialogs */
	AlertDialog.Builder languages = null;
	AlertDialog.Builder interests = null;
	AlertDialog.Builder error = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Retrieve all languages */
		ArrayList<String> lang = UserController.getAllLanguages();
		options_l = new CharSequence[lang.size()];
		for (int i = 0; i < lang.size(); i++)
			options_l[i] = lang.get(i);
		
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
			/* if specified username is already used, alert the user */
			if (UserController.isUserNameAlreadyUsed(((EditText)findViewById(R.id.username)).getText().toString())) {
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_alreadyused_title);
				error.setMessage(R.string.create_profile_error_alreadyused_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler());
				error.show();
			}
			else {
				System.out.println(lang.toArray(new String[lang.size()]));
				System.out.println(cat.get(2));
				UserController.addNewUser(((EditText)findViewById(R.id.username)).getText().toString(), options_a[selections_a].toString().toLowerCase(), lang.toArray(new String[lang.size()]), cat);
				intent = new Intent(this, Connect.class);
				startActivity(intent);
			}
			break;
		case R.id.ages:
			AlertDialog.Builder ages = new AlertDialog.Builder(this);
			ages.setTitle(R.string.create_profile_age_title);
			ages.setSingleChoiceItems(options_a, selections_a, new DialogSingleSelectionClickHandler());
			ages.setPositiveButton("OK", new DialogButtonClickHandler());
			ages.show();
			break;
		case R.id.choose_languages:
			languages = new AlertDialog.Builder(this);
			languages.setTitle(R.string.create_profile_languages_title);
			languages.setMultiChoiceItems(options_l, selections_l, new DialogSelectionClickHandler());
			languages.setPositiveButton("OK", new DialogButtonClickHandler());
			languages.show();
			break;
		case R.id.choose_interests:
			interests = new AlertDialog.Builder(this);
			interests.setTitle(R.string.create_profile_interests_title);
			interests.setMultiChoiceItems(options_i, selections_i, new DialogSelectionClickHandler());
			interests.setPositiveButton("OK", new DialogButtonClickHandler());
			interests.show();
			break;
		}
	}

	public class DialogSelectionClickHandler implements DialogInterface.OnMultiChoiceClickListener {
		public void onClick(DialogInterface dialog, int clicked, boolean selected) {
			if (dialog == languages)
				selections_l[clicked] = selected;
			if (dialog == interests)
				selections_i[clicked] = selected;
		}
	}

	public class DialogSingleSelectionClickHandler implements android.content.DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			selections_a = which;
		}
	}

	public class DialogButtonClickHandler implements DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int clicked) {
			switch(clicked)	{
				case DialogInterface.BUTTON_POSITIVE:
					if (dialog == languages) {
						System.out.println("lolo");
						for (int i = 0; i < options_l.length; i++) {
							if (selections_l[i]) {
								lang.add(options_l[i].toString());
							}
						}
					}
					
					if (dialog == interests) {
						Category category = null;
						System.out.println("ok");
						for (int i = 0; i < options_i.length; i++) {
							if (selections_i[i]) {
								System.out.println(options_i[i]);
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
