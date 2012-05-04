package com.mobilecityguide.datamappers;

import android.content.Context;

import com.mobilecityguide.gateways.CategoryGateway;
import com.mobilecityguide.gateways.RecordSet;
import com.mobilecityguide.gateways.SQL.SQLCategoryGateway;


public class CategoryMapper {

	private CategoryGateway categoryGateway;

	public CategoryMapper(Context context) {
		this.categoryGateway = new SQLCategoryGateway(context); 
	}

	public int getCategoryID(String categoryName, String language){
		RecordSet r = categoryGateway.getCategoryID(categoryName, language); 
		int ID = 0;
		try{
			if (r.first())
				ID = r.getInt("categoryID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ID==0)
			System.out.println("error : categoryID == 0 ");
		return ID;
	}
}

