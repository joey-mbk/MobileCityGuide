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
	
	//Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
	public boolean onCreateOptionsMenu(Menu menu) {

		//Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
		MenuInflater inflater = getMenuInflater();
		//Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);
		
		return true;

	}

	//Méthode qui se déclenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		//On regarde quel item a été cliqué grâce à son id et on déclenche une action
		switch (item.getItemId()) {
		case R.id.main_menu:
			intent = new Intent(this, MainMenu.class);
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
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
		case R.id.guide_tour_button:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
		}
	}
}
