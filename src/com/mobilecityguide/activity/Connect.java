package com.mobilecityguide.activity;

import java.util.ArrayList;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.activity.MyPlace.Item_Adapter;
import com.mobilecityguide.activity.MyPlace.UserRecord;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Connect extends Activity implements OnClickListener {
	
	ListView myList;
	 
    
	 
    String[] listContent = {
 
            "January",
 
            "February",
 
            "March",
 
            "April",
 
            "May",
 
            "June",
 
            "July",
 
            "August",
 
            "September",
 
            "October",
 
            "November",
 
            "December"
 
    };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);

		myList = (ListView)findViewById(R.id.list);



		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,

				android.R.layout.simple_list_item_1,

				listContent);

		myList.setAdapter(adapter);

		setListeners();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu_2, menu);
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
		((ListView)findViewById(R.id.list)).setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView arg0, View v, int position, long id) {
			// Nous définissons notre intent en lui disant quelle classe il faut utiliser
			Intent viewEntry= new Intent(getApplicationContext(),MobileCityGuideActivity.class);
			// On lui transmet des paramètres, ici la position de l'entry du  feed que l'on voudra ouvrir
			// On peut passer tous les types primitifs (long, int , boolean)
			viewEntry.putExtra("idEntry", position);
			// On démarre l'activity
			startActivity(viewEntry);
			// On ferme l'activity en cours
			finish();
			}
			});

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
