package com.mobilecityguide.activity;

import java.util.ArrayList;

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

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;

public class CitiesList extends Activity implements OnClickListener, OnItemClickListener {

	private String[]citiesList;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city);
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
		case R.id.change_user:
			intent = new Intent(this, Connect.class);
			startActivity(intent);
			return true;
		case R.id.change_city:
			intent = new Intent(this, CitiesList.class);
			startActivity(intent);
			return true;
		case R.id.edit_profile:
			intent = new Intent(this, MobileCityGuideActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	private void setListeners() {
		ListView citiesListView = (ListView)findViewById(R.id.listView1);
		//Remplissage de la liste de nom d'utilisateur
		ArrayList<String> citiesArrayList;
		try {
			citiesArrayList = POIController.getCitiesNames();
			citiesList = new String[citiesArrayList.size()];
			citiesArrayList.toArray(citiesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		citiesListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,citiesList));
		citiesListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			UserController.setCity((citiesList[(int) id]));
			System.out.println(UserController.getCity());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		startActivity(new Intent(this, CitiesList.class));
	}

	public void onClick(View v) {

	}
}
