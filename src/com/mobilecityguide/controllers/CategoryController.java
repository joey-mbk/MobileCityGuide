package com.mobilecityguide.controllers;

import java.util.HashMap;

import com.mobilecityguide.MobileCityGuideActivity;
import com.mobilecityguide.datamappers.CategoryMapper;

public class CategoryController {
	
	public static int  getCategoryID(String categoryName, String language){
		CategoryMapper categoryMapper = new CategoryMapper(MobileCityGuideActivity.context);
		return categoryMapper.getCategoryID(categoryName, language);
	}
	
	public static HashMap<String, String> gettAllCategories(){
		//TODO
		return null;
	} 

}
