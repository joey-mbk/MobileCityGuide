package com.mobilecityguide.controllers;

import java.util.ArrayList;

import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.User;
/*
 * Pour créer un nouvel utilisateur:
 * 
 * User newUser = new User();
 * newUser.set...;
 * setActiveUser(newUser);
 * ( addUser(newUser); )  
 */
public class UserController {

	public static User activeUser = null;
	public static String userName = null; 
	public static UserMapper userMapper;

	public static void setActiveUser(User activeUser) {
		UserController.activeUser = activeUser;
		userName = activeUser.getName();
	}
	
	public void saveUser(){
		userMapper.save(activeUser);
		userMapper.setUserName(activeUser,userName);
	}
	
	public void addUser(){
		userMapper.addUser(activeUser);
	}
	
	public void delUser(){
		userMapper.deleteUser(activeUser.getName());
	}
	
    public void setUserName(String name){
    	activeUser.setName(name);
    }
    
	public void setUserAge(String age){
	    activeUser.setAge(age);	
	}
	
	public void setUserLanguages(String[]languages){
		activeUser.setLanguage(languages);
	}
	
	public void setUserItineraryList(ArrayList<Itinerary> userItineraryList){
		activeUser.setUserItineraryList(userItineraryList);
	}
	
	public void setUserCategory(ArrayList<String> userCategoryList){
		activeUser.setUserCategoryList(userCategoryList);
	}
	
	public ArrayList<String> getAllUsersNames(){
		return userMapper.getAllUsersNames();
		 
	}
	
}
