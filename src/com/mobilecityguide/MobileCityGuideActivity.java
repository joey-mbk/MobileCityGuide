package com.mobilecityguide;

import java.util.ArrayList;

import com.mobilecityguide.controllers.CityController;
import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.models.User;

import android.app.Activity;
import android.os.Bundle;

public class MobileCityGuideActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserMapper userMapper = new UserMapper(this);
        User user = null;
        try {
			user = userMapper.getUser("Maxime");
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
	        
	        /* Testing POI */
	        CityController.city = "Louvain-La-Neuve";
	        POIMapper poiMapper = new POIMapper(this);
	        
	        setContentView(R.layout.main);
        } else {
        	System.out.println("NULL !!!!");
        }
    }
}