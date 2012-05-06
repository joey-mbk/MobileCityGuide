package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mobilecityguide.datamappers.CategoryMapper;
import com.mobilecityguide.models.Category;

public class CategoryController {
	
	public static HashMap<Integer, Category> fetchedCategories = new HashMap<Integer, Category>();
	
	public static CategoryMapper categoryMapper;	
	
	public static Category getCategory(int ID){		
		return categoryMapper.getCategory(ID);
	}
	
	public static ArrayList<String> getAllCategoriesNames(){
		
		ArrayList<String> categories = new ArrayList<String>(); // List of all categories in activeUser language
		HashMap <String, String> allCategories = categoryMapper.getAllCategories();
        String[]languages = UserController.activeUser.getLanguage();
		for(Entry<String, String> entry : allCategories.entrySet()) {
		    if (languages[0].equals(entry.getKey()))//TODO gestion langues
		    	categories.add(entry.getValue());
		}
		return categories;
	}
	
	/*
	 * Return category name according to the active user language
	 */ 
	public static String getCategoryTitle(Category category){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(category.getCategory().containsKey(language))
			return category.getCategory(language);
		}
		return null;
	}

}
