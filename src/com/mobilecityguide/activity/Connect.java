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
		//View createButton = findViewById(R.id.create_user);
		//createButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		/*case R.id.create_user:
			intent = new Intent(this, CreateUser.class);
			startActivity(intent);
			break;*/
		}
	}
}
