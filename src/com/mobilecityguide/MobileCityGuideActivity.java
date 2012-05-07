package com.mobilecityguide;

import com.mobilecityguide.R;
import com.mobilecityguide.activity.About;
import com.mobilecityguide.activity.Connect;
import com.mobilecityguide.activity.Help;
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
		setContentView(R.layout.connect);
		setListeners();
	}

	//M�thode qui se d�clenchera lorsque vous appuierez sur le bouton menu du t�l�phone
	public boolean onCreateOptionsMenu(Menu menu) {

		//Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
		MenuInflater inflater = getMenuInflater();
		//Instanciation du menu XML sp�cifier en un objet Menu
		inflater.inflate(R.layout.menu_2, menu);

		return true;
	}

	//M�thode qui se d�clenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		//On regarde quel item a �t� cliqu� gr�ce � son id et on d�clenche une action
		switch (item.getItemId()) {
		case R.id.quit:
			//Pour fermer l'application il suffit de faire finish()
            finish();
            return true;
		}
		return false;
	}

	private void setListeners() {
		//View connectButton = findViewById(R.id.connect_button);
		//connectButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		
		}
	}
}