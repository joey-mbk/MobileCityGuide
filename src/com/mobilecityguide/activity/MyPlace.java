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
		View addButton = findViewById(R.id.add_button);
		addButton.setOnClickListener(this);
		View dellButton = findViewById(R.id.dell_button);
		dellButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.add_button:
			intent = new Intent(this, AddItem.class);
			startActivity(intent);
			break;
		case R.id.dell_button:
			intent = new Intent(this, DellItem.class);
			startActivity(intent);
			break;
		}
	}
}
