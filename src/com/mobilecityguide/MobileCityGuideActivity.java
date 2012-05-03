package com.mobilecityguide;

import com.mobilecityguide.R;
import com.mobilecityguide.activity.Connect;
import com.mobilecityguide.activity.Registrer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MobileCityGuideActivity extends Activity implements OnClickListener {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListeners();
    }

	private void setListeners() {
        View myVisitsButton = findViewById(R.id.bconnect);
        myVisitsButton.setOnClickListener(this);
        View settingsButton = findViewById(R.id.bregistrer);
        settingsButton.setOnClickListener(this);
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
		}
	}
}