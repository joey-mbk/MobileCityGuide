package com.mobilecityguide.gateways;

public interface POIGateway {

	RecordSet getPOI(int id) throws Exception;
	RecordSet getPOICategories(int id) throws Exception;
	RecordSet getPOINames(int id) throws Exception;
	RecordSet getDescriptions(int id) throws Exception;
	RecordSet getLocGuidelines(int id) throws Exception;
	RecordSet getImages(int id) throws Exception;
	RecordSet getPOIsOfCity(String city) throws Exception;
	RecordSet getPOIsOfCategory(String city, int category) throws Exception;
	RecordSet getAllCities();

}
