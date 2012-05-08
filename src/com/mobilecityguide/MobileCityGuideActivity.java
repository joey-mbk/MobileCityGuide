package com.mobilecityguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.activity.Cities;
import com.mobilecityguide.activity.Connect;
import com.mobilecityguide.activity.Free_walk;
import com.mobilecityguide.activity.Guided_tour;

public class MobileCityGuideActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setListeners();
	}

	private void setListeners() {
		View enterButton = findViewById(R.id.contentLayout);
		enterButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.contentLayout:
			intent = new Intent(this, Connect.class);
			startActivity(intent);
			break;
		}
	}
}