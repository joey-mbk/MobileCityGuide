package com.mobilecityguide;

import java.util.ArrayList;

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
        UserMapper userMapper = new UserMapper();
        User user = null;
        try {
			user = userMapper.getUser("Hervé");
		} catch (Exception e) {
			System.out.println("Error");
		}
        System.out.println("Name: "+user.getName());
        System.out.println("Age: "+user.getAge());
        ArrayList<String> list = user.getLanguage();
        for (String lang:list)
        	System.out.println("Language: "+lang);
        setContentView(R.layout.main);
    }
}