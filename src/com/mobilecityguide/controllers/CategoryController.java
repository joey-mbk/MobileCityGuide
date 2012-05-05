package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.datamappers.CategoryMapper;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.POI;

public class CategoryController {
	
	public static HashMap<Integer, Category> fetchedCategories = new HashMap<Integer, Category>();
	
	public static CategoryMapper categoryMapper;	
	
	public static Category getCategory(int ID){		
		return categoryMapper.getCategory(ID);
	}
	
	public static ArrayList<String> getAllCategoriesNames(){
		
		ArrayList<String> categories = new ArrayList<String>(); // List of all categories in activeUser language
		HashMap <String, String> allCategories = categoryMapper.getAllCategories();

		for(Entry<String, String> entry : allCategories.entrySet()) {
		    if (UserController.activeUser.getLanguage().equals(entry.getKey()))
		    	categories.add(entry.getValue());
		}
		return categories;
	}

}
