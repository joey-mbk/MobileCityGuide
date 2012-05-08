package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.R;

public class PoisList extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pois_list);
		setListeners();
	}

	private void setListeners() {
		/*View addPoiButton = findViewById(R.id.add_poi);
		addPoiButton.setOnClickListener(this);*/

		/*View startButton = findViewById(R.id.start);
		startButton.setOnClickListener(this);*/
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {

		}
	}
}
