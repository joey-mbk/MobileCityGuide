package com.mobilecityguide.activity;

import java.util.ArrayList;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;

import com.mobilecityguide.controllers.UserController;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Connect extends Activity implements OnClickListener, OnItemClickListener {
    
	private String[]userNamesList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);

		setListeners();
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.quit:
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	


	private void setListeners() {
		View createButton = findViewById(R.id.create_profile);
		createButton.setOnClickListener(this);

		ListView userListView = (ListView)findViewById(R.id.list);
		//Remplissage de la liste de nom d'utilisateur
		ArrayList<String>userNamesArrayList = UserController.getAllUsersNames();
		userNamesList = new String[userNamesArrayList.size()];
		userNamesArrayList.toArray(userNamesList);
		
		userListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userNamesList));
		userListView.setOnItemClickListener(this);

	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			UserController.setActiveUser(UserController.getUser(userNamesList[(int) id]));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		startActivity(new Intent(this, CitiesList.class));
	}
	
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.create_profile:
			intent = new Intent(this, CreateProfile.class);
			startActivity(intent);
			break;
		}
	}
}
