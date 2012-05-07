package com.mobilecityguide.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mobilecityguide.datamappers.CategoryMapper;
import com.mobilecityguide.models.Category;

public class CategoryController {
	
	public static HashMap<Integer, Category> fetchedCategories = new HashMap<Integer, Category>();
	public static HashMap<String, Integer> titlesIDCategoriesMap = new HashMap<String, Integer>();
	
	public static CategoryMapper categoryMapper;	
	
	public static Category getCategory(int ID){		
		return categoryMapper.getCategory(ID);
	}
	
	public static Category getCategory(String name) {
		int id = titlesIDCategoriesMap.get(name);
		return categoryMapper.getCategory(id);
	}
	
	/*
	 * Return category name according to the active user language
	 */ 
	public static String getCategoryTitle(Category category){
		String [] languages = UserController.activeUser.getLanguage();
		for (String language : languages){
		if(category.getCategory().containsKey(language)){
			return category.getCategory(language);
		}
		}
		return null;
	}
	
	public static String getCategoryTitle(int id){
		String [] languages = UserController.activeUser.getLanguage();
		HashMap<String,String> titles = categoryMapper.getCategoryTitles(id);
		for (String language : languages){
			if(titles.containsKey(language)){
				String title = titles.get(language);
				titlesIDCategoriesMap.put(title, id); //To keep the link between title and id
				return title;
			}
		}
		return null;
	}
	
	/*
	 * Return categories titles according to the active user language
	 */ 
	public static ArrayList<String> getCategoriesTitles(ArrayList<Integer>itinerariesID){
		ArrayList<String>titles = new ArrayList<String>();		
		for(Integer id: itinerariesID)
			titles.add(getCategoryTitle(id));
		return titles;
	}
	
	/*
	 * Return list of all categories titles according to the active user language
	 */ 
	public static ArrayList<String> getAllCategoriesTitles() throws Exception{
		return getCategoriesTitles(getAllCategoriesID());
	}
	
	public static ArrayList<Integer> getAllCategoriesID() throws Exception{
		return categoryMapper.getAllCategoriesID();
	}
}
