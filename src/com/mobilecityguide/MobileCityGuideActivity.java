package com.mobilecityguide;

import java.util.ArrayList;

import com.mobilecityguide.controllers.GPSController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.exceptions.GatewayException;
import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.SQL.SQLGateway;
import com.mobilecityguide.models.User;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;

public class MobileCityGuideActivity extends Activity {
	
	public static GPSController gps_controller;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SQLGateway(this);
        UserController.userMapper = new UserMapper(this);
        User user = null;
        try {
			user = UserController.userMapper.getUser("Maxime");
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
	        setContentView(R.layout.main);
        } else {
        	System.out.println("NULL !!!!");
        }
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        gps_controller = new GPSController(locationManager);
    }
}