package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

public class Itinerary extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_items);
	}
	
	//M�thode qui se d�clenchera lorsque vous appuierez sur le bouton menu du t�l�phone
	public boolean onCreateOptionsMenu(Menu menu) {

		//Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
		MenuInflater inflater = getMenuInflater();
		//Instanciation du menu XML sp�cifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);
		
		return true;

	}

	//M�thode qui se d�clenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		//On regarde quel item a �t� cliqu� gr�ce � son id et on d�clenche une action
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
	
}
