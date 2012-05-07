package com.mobilecityguide.activity;

import com.mobilecityguide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Connect extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);
		setListeners();
	}

	private void setListeners() {
		//View loginButton = findViewById(R.id.login_button);
		//loginButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		/*case R.id.login_button:
			intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			break;*/
		}
	}
}
