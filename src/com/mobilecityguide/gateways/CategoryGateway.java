package com.mobilecityguide.gateways;

public interface CategoryGateway {

	public RecordSet getCategory(int ID);
	public RecordSet getCategoryTitles(int id);
	RecordSet getAllCategoriesID();

}
