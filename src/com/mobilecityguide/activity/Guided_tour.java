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

public class Guided_tour extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guided_tour);
		setListeners();
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
			case R.id.quit:
					intent = new Intent(this, MobileCityGuideActivity.class);
					startActivity(intent);
					return true;
			case R.id.change_user:
				intent = new Intent(this, Connect.class);
				startActivity(intent);
				return true;
			case R.id.change_city:
				intent = new Intent(this, Cities.class);
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
		View freeWalkButton = findViewById(R.id.create_itinerary_button);
		freeWalkButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.create_itinerary_button:
			//intent = new Intent(this, Free_walk.class);
			//startActivity(intent);
			break;
		}
	}
}
