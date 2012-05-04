package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

public class DellItem extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_items);
		setListeners();
	}

	private void setListeners() {
		View cancelButton = findViewById(R.id.CancelButton);
		cancelButton.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		
		}
	}
}
