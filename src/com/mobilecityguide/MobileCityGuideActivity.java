package com.mobilecityguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MobileCityGuideActivity extends Activity {
	
	//On déclare toutes les variables dont on aura besoin
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrer);
        
     /* //On récupère tout les éléments de notre interface graphique grâce aux ID
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);*/

    }
}