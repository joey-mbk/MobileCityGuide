package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.R;

public class Settings extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
        setListeners();
	}
	private void setListeners() {
		View cancelButton = findViewById(R.id.CancelButton);
		cancelButton.setOnClickListener(this);
        View itineraryButton = findViewById(R.id.itinerary_bouton);
        itineraryButton.setOnClickListener(this);
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
		case R.id.itinerary_bouton:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
		case R.id.profil_user_button:
			intent = new Intent(this, Itinerary.class);
			startActivity(intent);
			break;
	}
	}
}
