package com.mobilecityguide.datamappers;

import java.util.HashMap;

import android.content.Context;

import com.mobilecityguide.exceptions.RecordSetException;
import com.mobilecityguide.gateways.CategoryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.SQL.SQLCategoryGateway;


public class CategoryMapper {

	private CategoryGateway categoryGateway;

	public CategoryMapper(Context context) {
		this.categoryGateway = new SQLCategoryGateway(context); 
	}

	public HashMap<String, String> getCategory(int ID){
		RecordSet r = categoryGateway.getCategory(ID);
		HashMap<String, String> category = new HashMap<String, String>();;
		try {
			while (r.next())
				category.put(r.getString("language"), "title");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return category;
	}

	public HashMap<String, String> getAllCategories(){
		RecordSet r = categoryGateway.getAllCategories();
		HashMap<String, String> category = new HashMap<String, String>();;
		try {
			while (r.next())
				category.put(r.getString("language"), "title");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return category;
	}
}

