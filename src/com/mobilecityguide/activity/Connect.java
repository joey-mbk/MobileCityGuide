package com.mobilecityguide.activity;

import com.mobilecityguide.MobileCityGuideActivity;
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
        View loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(this);
        View cancelButton = findViewById(R.id.CancelButton);
        cancelButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.LoginButton:
			intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			break;
		case R.id.CancelButton:
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			break;
	}
	}
}
