package com.mobilecityguide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobilecityguide.R;

public class MyPlace extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.items_list);
		setListeners();
	}

	private void setListeners() {
		/*View cancelButton = findViewById(R.id.CancelButton);
		cancelButton.setOnClickListener(this);
		View addButton = findViewById(R.id.addButton);
		addButton.setOnClickListener(this);
		View dellButton = findViewById(R.id.dellButton);
		dellButton.setOnClickListener(this);*/
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		/*case R.id.CancelButton:
			intent = new Intent(this, MainMenu.class);
			startActivity(intent);
			break;
		case R.id.addButton:
			intent = new Intent(this, AddItem.class);
			startActivity(intent);
			break;
		case R.id.dellButton:
			intent = new Intent(this, DellItem.class);
			startActivity(intent);
			break;*/
		}
	}
}
