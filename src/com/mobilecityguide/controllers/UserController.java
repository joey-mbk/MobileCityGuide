package com.mobilecityguide.controllers;

import java.util.ArrayList;

import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.User;

public class UserController {

	public static User activeUser = null;
	public static String userName = null; 
	public static UserMapper userMapper;

	public static void setActiveUser(User activeUser) {
		UserController.activeUser = activeUser;
		userName = activeUser.getName();
	}

	public static void saveUser(){
		userMapper.save(activeUser);
		userMapper.setUserName(activeUser,userName);
	}

	public static void addNewUser(User newUser,String name ,String age,String[]languages, ArrayList<String> userCategoryList){
		newUser.setName(name);
		newUser.setLanguage(languages);
		newUser.setAge(age);
		newUser.setUserCategoryList(userCategoryList);
		setActiveUser(newUser);
		userMapper.addUser(activeUser);
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

	public static void setActiveUserItineraryList(ArrayList<Itinerary> userItineraryList){
		activeUser.setUserItineraryList(userItineraryList);
	}

	public static void setActiveUserCategory(ArrayList<String> userCategoryList){
		activeUser.setUserCategoryList(userCategoryList);
	}

	public static ArrayList<String> getAllUsersNames(){
		return userMapper.getAllUsersNames();

	}

}
