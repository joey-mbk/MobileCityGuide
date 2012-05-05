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

public class Settings extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
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
		case R.id.settings:
			intent = new Intent(this, Settings.class);
			startActivity(intent);
			return true;
		case R.id.disconnect:
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
	
	private void setListeners() {
		View cancelButton = findViewById(R.id.CancelButton);
		cancelButton.setOnClickListener(this);
        View profil_userBouton = findViewById(R.id.profil_user_button);
        profil_userBouton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.CancelButton:
			intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			break;
		case R.id.profil_user_button:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
	}
	}
}
