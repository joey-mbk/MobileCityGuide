package com.mobilecityguide.activity;

import android.app.Activity;
import android.os.Bundle;

import com.mobilecityguide.R;

public class MyPlace extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_places);
	}
}
