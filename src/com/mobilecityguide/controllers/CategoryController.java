package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.datamappers.CategoryMapper;

public class CategoryController {
	
	public static CategoryMapper categoryMapper;	
	
	public static HashMap<String, String> getCategory(int ID){
		return categoryMapper.getCategory(ID);
	}
	
	public static ArrayList<String> getAllCategories(){
		
		ArrayList<String> categories = new ArrayList<String>(); // List of all categories in activeUser language
		HashMap <String, String> allCategories = categoryMapper.getAllCategories();
		for(Entry<String, String> entry : allCategories.entrySet()) {
		    if (UserController.activeUser.getLanguage().equals(entry.getKey()))
		    	categories.add(entry.getValue());
		}
		return categories;
	}

}
