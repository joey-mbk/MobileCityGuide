package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.mobilecityguide.R;

public class Registrer extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrer);
		setListeners();
	}

	private void setListeners() {
		View registerButton = findViewById(R.id.save_button);
		registerButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.register_button:
			intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			break;
		}
	}
}
