package com.mobilecityguide.controllers;

import java.util.ArrayList;

import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.User;

public class UserController {

	public static User activeUser = null;
	public static String userName = null; 
	public static String city; // city which the user is currently in
	public static Itinerary selectedItinerary;
	
	public static UserMapper userMapper;

	public static void setActiveUser(User activeUser) {
		UserController.activeUser = activeUser;
		userName = activeUser.getName();
	}

	public static void saveUser(){
		userMapper.save(activeUser,userName);
	}
    
	public static void addNewUser(String name ,String age,String[]languages, ArrayList<Category> userCategories) {
		selectedItinerary = null;
		city = null;
		User newUser = new User();
		newUser.setName(name);
		newUser.setLanguage(languages);
		newUser.setAge(age);
		newUser.setUserCategories(userCategories);
		setActiveUser(newUser);
		userMapper.addUser(activeUser);
	}
	
	public static User getUser(String name) throws Exception{
		return userMapper.getUser(name);
	}

	public static void delActiveUser(){
		userMapper.deleteUser(activeUser.getName());
	}

	public static void setActiveUserName(String name){
		activeUser.setName(name);
	}

	public static void setActiveUserAge(String age){
		activeUser.setAge(age);	
	}

	public static void setActiveUserLanguages(String[]languages){
		activeUser.setLanguage(languages);
	}

	public static void setActiveUserItinerariesID(ArrayList<Integer> userItinerariesID){
		activeUser.setUserItinerariesID(userItinerariesID);
	}
	
	public static ArrayList<String> getActiveUserItinerariesNames(){
		return ItineraryController.getItinerariesTitles(activeUser.getUserItinerariesID());
	}

	public static void setActiveUserCategory(ArrayList<Category> userCategories){
		activeUser.setUserCategories(userCategories);
	}
	
	public static void addCategoryForActiveUser(Category category){
		activeUser.addCategory(category);
	}
	
	public static void delCategoryForActiveUser(Category category){
		activeUser.delCategory(category);
	}
    
	/*
	 * Return active user categories titles according to the active user language
	 */ 
	public static ArrayList<String> getActiveUserCategoriesNames(){
		ArrayList<String> categoriesNames = new ArrayList<String>();
		ArrayList<Category> categories= activeUser.getUserCategories();
		for (Category category : categories){
			categoriesNames.add(CategoryController.getCategoryTitle(category.getId()));
		}
		return categoriesNames;
	}
	public static ArrayList<String> getAllUsersNames(){
		return userMapper.getAllUsersNames();

	}
	
	public static String getCity() {
		return city;
	}

	public static void setCity(String city) {
		UserController.city = city;
	}
	
	public static ArrayList<String> getAllLanguages(){
 		return userMapper.getAllLanguages();
	}
	
	public static boolean isUserNameAlreadyUsed(String name){
		return getAllUsersNames().contains(name);
		
	}
	
	public static void addUserItinerary(Itinerary itinerary) throws Exception{
		ItineraryController.addItinerary(itinerary);
		setActiveUser(getUser(activeUser.getName()));
	}
	
	public static void delUserItinerary(Itinerary itinerary) throws Exception{
		ItineraryController.deleteItinerary(itinerary);
		setActiveUser(getUser(activeUser.getName()));
	}

}
