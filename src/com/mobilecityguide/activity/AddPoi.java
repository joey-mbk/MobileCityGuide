package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

public class AddPoi extends Activity implements OnClickListener , OnItemClickListener {

	private String[]poiNamesList;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_poi);
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
		ListView poiListView = (ListView)findViewById(R.id.list);
		//Remplissage de la liste de nom des poi
		POI[] poiList =null;
		try {
			poiList = POIController.getPOIOfCity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		poiNamesList = new String[poiList.length];
		for(int i=0; i<poiList.length;i++)
			poiNamesList[i]=POIController.getPOIName(poiList[i]);		
		poiListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,poiNamesList));
		poiListView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long id) {
		try {
			System.out.println((poiNamesList[(int) id]));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		//startActivity(new Intent(this, PoisList.class));
	}

	public void onClick(View v) {
		/*Intent intent;
		switch (v.getId()) {
		case R.id.start:
			intent = new Intent(this, .class);
			startActivity(intent);
			break;
		}*/
	}
}