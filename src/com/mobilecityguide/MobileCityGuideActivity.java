package com.mobilecityguide;

import com.mobilecityguide.R;
import com.mobilecityguide.activity.About;
import com.mobilecityguide.activity.Connect;
import com.mobilecityguide.activity.Help;
import com.mobilecityguide.activity.MainMenu;
import com.mobilecityguide.activity.Registrer;
import com.mobilecityguide.activity.Settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MobileCityGuideActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setListeners();
	}

	//Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
	public boolean onCreateOptionsMenu(Menu menu) {

		//Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
		MenuInflater inflater = getMenuInflater();
		//Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menu_2, menu);

		return true;
	}

	//Méthode qui se déclenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		//On regarde quel item a été cliqué grâce à son id et on déclenche une action
		switch (item.getItemId()) {
		case R.id.quit:
			//Pour fermer l'application il suffit de faire finish()
            finish();
            return true;
		}
		return false;
	}

	private void setListeners() {
		View connectButton = findViewById(R.id.bconnect);
		connectButton.setOnClickListener(this);
		View registrerButton = findViewById(R.id.bregistrer);
		registrerButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.babout);
		aboutButton.setOnClickListener(this);
		View helpButton = findViewById(R.id.bhelp);
		helpButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bconnect:
			intent = new Intent(this, Connect.class);
			startActivity(intent);
			break;
		case R.id.bregistrer:
			intent = new Intent(this, Registrer.class);
			startActivity(intent);
			break;
		case R.id.bhelp:
			intent = new Intent(this, Help.class);
			startActivity(intent);
			break;
		case R.id.babout:
			intent = new Intent(this, About.class);
			startActivity(intent);
			break;
		}
	}
}