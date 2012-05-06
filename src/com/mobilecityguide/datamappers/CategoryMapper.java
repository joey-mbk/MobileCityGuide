package com.mobilecityguide.datamappers;

import java.util.HashMap;

import android.content.Context;

import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.gateways.CategoryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.SQL.SQLCategoryGateway;
import com.mobilecityguide.models.Category;


public class CategoryMapper {

	private CategoryGateway categoryGateway;

	public CategoryMapper(Context context) {
		this.categoryGateway = new SQLCategoryGateway(context); 
	}

	public Category getCategory(int ID){
		
		/* if Category has already been fetched from the database, return it */
		Category category = CategoryController.fetchedCategories.get(new Integer(ID));
		if (category != null)
			return category;
		
		RecordSet r = categoryGateway.getCategory(ID);
		HashMap<String, String> categoriesMap = new HashMap<String, String>();;
		try {
			while (r.next())
				categoriesMap.put(r.getString("language"), r.getString("title"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		category = new Category(ID,categoriesMap);

		return category;
	}

	public HashMap<String, String> getAllCategories(){
		RecordSet r = categoryGateway.getAllCategories();
		HashMap<String, String> category = new HashMap<String, String>();;
		try {
			while (r.next())
				category.put(r.getString("language"), r.getString("title"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return category;
	}
}

