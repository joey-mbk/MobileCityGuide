package com.mobilecityguide;

import com.mobilecityguide.R;
import com.mobilecityguide.activity.About;
import com.mobilecityguide.activity.Connect;
import com.mobilecityguide.activity.Help;
import com.mobilecityguide.activity.Registrer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MobileCityGuideActivity extends Activity implements OnClickListener {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListeners();
    }

	private void setListeners() {
        View connectButton = findViewById(R.id.bconnect);
        connectButton.setOnClickListener(this);
        View registrerButton = findViewById(R.id.bregistrer);
        registrerButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.babout);
        aboutButton.setOnClickListener(this);
        View helpButton = findViewById(R.id.bhelp);
        helpButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bconnect:
			intent = new Intent(this, Connect.class);
			startActivity(intent);
			break;
		case R.id.bregistrer:
			intent = new Intent(this, Registrer.class);
			startActivity(intent);
			break;
		case R.id.bhelp:
			intent = new Intent(this, Help.class);
			startActivity(intent);
			break;
		case R.id.babout:
			intent = new Intent(this, About.class);
			startActivity(intent);
			break;
		}
	}
}