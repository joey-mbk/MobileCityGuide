package com.mobilecityguide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MobileCityGuideActivity extends Activity {
	
	//On d�clare toutes les variables dont on aura besoin
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrer);
        
     /* //On r�cup�re tout les �l�ments de notre interface graphique gr�ce aux ID
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);*/

    }
}