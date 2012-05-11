package com.mobilecityguide.activity;

import java.util.HashMap;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.R;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.models.POI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PoiDetails extends Activity implements OnClickListener {

	private boolean inItinerary;
	private int step;

	ImageView image;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Retrieve the selected POI */
		Bundle extras = getIntent().getExtras(); // retrieve the variables from previous intent
		String poiName = null;
		int poiID = 0;
		POI poi = null;
		try {
			if (extras != null)
				this.inItinerary = extras.getBoolean("itinerary");
			if (this.inItinerary) {
				this.step = extras.getInt("step");
				poiID = extras.getInt("poi");
				poi = POIController.getPOI(poiID);
			}
			else {
				poiName = extras.getString("poi"); // retrieve the name of the POI
				poi = POIController.getPOI(poiName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] languages = UserController.activeUser.getLanguage();
		String desc = null;
		for (String lang : languages) {
			desc = poi.getDescription(UserController.activeUser.getAge(), lang);
			if (desc != null)
				break;
		}

		/* Set the details in corresponding text fields */
		setContentView(R.layout.poi_details);
		((TextView) findViewById(R.id.description)).setText(desc);
		((TextView) findViewById(R.id.address)).setText(poi.getAddress());
		image = (ImageView) findViewById(R.id.imageView);
		
		String path = ((poi.getImages("day")).split("\\."))[0];
		
		if(path.equals("barb93"))
			image.setImageResource(R.drawable.barb93);
		else if(path.equals("barbe1"))
			image.setImageResource(R.drawable.barbe1);
		else if(path.equals("barbe2"))
			image.setImageResource(R.drawable.barbe2);
		else if(path.equals("barbe3"))
			image.setImageResource(R.drawable.barbe3);
		else if(path.equals("cinescope1"))
			image.setImageResource(R.drawable.cinescope1);
		else if(path.equals("cinescope2"))
			image.setImageResource(R.drawable.cinescope2);
		else if(path.equals("cinescope3"))
			image.setImageResource(R.drawable.cinescope3);
		else if(path.equals("creperie_bretone"))
			image.setImageResource(R.drawable.creperie_bretone);
		else if(path.equals("creperie_bretone2"))
			image.setImageResource(R.drawable.creperie_bretone2);
		else if(path.equals("esplanade"))
			image.setImageResource(R.drawable.esplanade);
		else if(path.equals("esplanade2"))
			image.setImageResource(R.drawable.esplanade2);
		else if(path.equals("gp1"))
			image.setImageResource(R.drawable.gp1);
		else if(path.equals("gp2"))
			image.setImageResource(R.drawable.gp2);
		else if(path.equals("gp3"))
			image.setImageResource(R.drawable.gp3);
		else if(path.equals("lac1"))
			image.setImageResource(R.drawable.lac1);
		else if(path.equals("lac2"))
			image.setImageResource(R.drawable.lac2);
		else if(path.equals("lac3"))
			image.setImageResource(R.drawable.lac3);
		else if(path.equals("sciences1"))
			image.setImageResource(R.drawable.sciences1);
		else if(path.equals("sciences2"))
			image.setImageResource(R.drawable.sciences2);
		else if(path.equals("sciences3"))
			image.setImageResource(R.drawable.sciences3);
		else if(path.equals("univ1"))
			image.setImageResource(R.drawable.univ1);
		else if(path.equals("univ2"))
			image.setImageResource(R.drawable.univ2);
		else if(path.equals("univ3"))
			image.setImageResource(R.drawable.univ3);
		else if(path.equals("wallons1"))
			image.setImageResource(R.drawable.wallons1);		
		else if(path.equals("museeherge31"))
			image.setImageResource(R.drawable.museeherge31);	
		else if(path.equals("museeherge5"))
			image.setImageResource(R.drawable.museeherge5);	
		else if(path.equals("museelln1"))
			image.setImageResource(R.drawable.museelln1);	
		else if(path.equals("museelln2"))
			image.setImageResource(R.drawable.museelln2);	

		/* if we're coming from an itinerary, show a 'Next' button */
		if (this.inItinerary) {
			Button container = new Button(this);
			/* check if it's the last poi in the itinerary */
			if (UserController.selectedItinerary.getPOIList().size() == step)
				container.setText("Finish");
			else
				container.setText("Next");
			container.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			container.setOnClickListener(this);
			container.setBackgroundResource(R.drawable.buttonroundedcorners);
			LinearLayout layout = (LinearLayout) findViewById(R.id.info_list);
			layout.addView(container);
		}
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
			intent = new Intent(this, CreateProfile.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	@Override
	public void onClick(View arg0) {
		/* check if it's the last poi in the itinerary */
		Intent intent;
		if (UserController.selectedItinerary.getPOIList().size() == step)
			intent = new Intent(this, ItinerariesList.class);
		else
			intent = new Intent(this, Directions.class); // get directions towards the next poi in the itinerary
		intent.putExtra("step", this.step+1);
		startActivity(intent);
	}
}
