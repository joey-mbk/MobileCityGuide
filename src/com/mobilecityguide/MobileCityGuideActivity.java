package com.mobilecityguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.activity.Connect;
import com.mobilecityguide.activity.CreateItinerary;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.datamappers.CategoryMapper;
import com.mobilecityguide.datamappers.ItineraryMapper;
import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.datamappers.UserMapper;

public class MobileCityGuideActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* Initialize the controllers */
		POIController.poiMapper = new POIMapper(this);
		CategoryController.categoryMapper = new CategoryMapper(this);
		ItineraryController.itineraryMapper = new ItineraryMapper(this);
		UserController.userMapper = new UserMapper(this);
		
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
			intent = new Intent(this, CreateItinerary.class);
			startActivity(intent);
			break;
		}
	}
}