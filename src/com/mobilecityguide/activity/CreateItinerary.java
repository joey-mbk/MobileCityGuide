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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.activity.CreateProfile.DialogButtonClickHandler;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;

public class CreateItinerary extends Activity implements OnClickListener {

	protected CharSequence[] options_t;
	protected int selections_t;

	/* Error dialog */
	AlertDialog.Builder error;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			/* Retrieve all category titles */
			ArrayList<String> titles = CategoryController.getAllCategoriesTitles();
			options_t = new CharSequence[titles.size()];
			for (int i = 0; i < titles.size(); i++)
				options_t[i] = titles.get(i);
		} catch (Exception e) {
			options_t = null;
			e.printStackTrace();
		}
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
			intent = new Intent(this, CreateProfile.class);
			startActivity(intent);
			return true;

		}
		return false;
	}


	private void setListeners() {
		View chooseThemeButton = findViewById(R.id.theme);
		chooseThemeButton.setOnClickListener(this);

		View saveButton = findViewById(R.id.add_pois);
		saveButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.theme:
			AlertDialog.Builder theme = new AlertDialog.Builder(this);
			theme.setTitle(R.string.createitinerary_theme_text);
			theme.setSingleChoiceItems(options_t, selections_t, new DialogSingleSelectionClickHandler());
			theme.setPositiveButton("OK", new DialogButtonClickHandler("theme"));
			theme.show();
			break;
		case R.id.add_pois:
			String name =((EditText)findViewById(R.id.itineraryname)).getText().toString();
			String selectedTheme = ((Button) findViewById(R.id.theme)).getText().toString();
			
			if(selectedTheme.equals("Theme of the itinerary")){
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.no_theme);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			else if(name == null || name.equals("") || !name.matches("[a-zA-Z0-9]+")){
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.create_itinerary_error_itineraryNameformat_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			else if(ItineraryController.isItineraryNameAlreadyUsed(name)){
				error = new AlertDialog.Builder(this);
				error.setTitle(R.string.create_profile_error_title);
				error.setMessage(R.string.create_itinerary_error_alreadyused_text);
				error.setPositiveButton("OK", new DialogButtonClickHandler("error"));
				error.show();
			}
			else
			{
				/* we create the itinerary object */
				Itinerary itinerary = new Itinerary();
				itinerary.setId(ItineraryController.itineraryMapper.getLastItineraryID()+1);
				itinerary.addTitle(UserController.activeUser.getLanguage()[0], ((EditText)findViewById(R.id.itineraryname)).getText().toString());				
				itinerary.setTheme(CategoryController.getCategory( ((Button) findViewById(R.id.theme)).getText().toString() ));


				/* add it to the database */
				try {
					ItineraryController.addItinerary(itinerary);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error while creating a new itinerary.");
				}

				intent = new Intent(this, ItinerariesList.class);
				startActivity(intent);
				Toast.makeText(this, R.string.createitinerary_added_text, Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	public class DialogSingleSelectionClickHandler implements android.content.DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			selections_t = which;
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
				if (window.equals("theme")) {
					Button button = (Button) findViewById(R.id.theme);
					button.setText(options_t[selections_t]);
				}
				break;

			}
		}
	}
}
