package com.mobilecityguide;

import java.util.ArrayList;

import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.controllers.GPSController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.models.POI;
import com.mobilecityguide.models.User;

import android.app.Activity;
import android.os.Bundle;

public class MobileCityGuideActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        POIController.poiMapper = new POIMapper(this); // keep this
        
        UserMapper userMapper = new UserMapper(this);
        User user = null;
        POI poi = null;
        UserController.city = "Louvain-La-Neuve";
        POIMapper poiMapper = new POIMapper(this);
        try {
			user = userMapper.getUser("Maxime");
			poi = poiMapper.getPOI(1);
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
        if (user != null) {
	        System.out.println("Name: "+user.getName());
	        System.out.println("Age: "+user.getAge());
	        String[] list = user.getLanguage();
	        for (String lang:list)
	        	System.out.println("Language: "+lang);
        } else {
        	System.out.println("NULL !!!!");
        }
        if (poi != null) {
	        System.out.println("Name: "+poi.getName(user.getLanguage()[0]));
	        System.out.println("Description: "+poi.getDescription(user.getAge(), user.getLanguage()[0]));
        } else {
        	System.out.println("NULL !!!!");
        }
        
        setContentView(R.layout.main);
    }
}