package com.mobilecityguide;

import java.util.ArrayList;

import com.mobilecityguide.datamappers.CategoryMapper;
import com.mobilecityguide.datamappers.ItineraryMapper;
import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.datamappers.UserMapper;

import android.app.Activity;
import android.os.Bundle;

public class MobileCityGuideActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		POIController.poiMapper = new POIMapper(this); // keep this
		CategoryController.categoryMapper = new CategoryMapper(this);
		ItineraryController.itineraryMapper = new ItineraryMapper(this);
		UserController.userMapper = new UserMapper(this);


		/*
		 * Choix de l'utilisateur
		 */
		ArrayList<String>usersList = UserController.getAllUsersNames();
		System.out.println("*******Liste des utilisateurs*******");
		for(String userName: usersList)
			System.out.println(userName);
		try {
			UserController.setActiveUser(UserController.getUser(usersList.get(0)));
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("Error get user");
		}

		/*
		 * Affichage des données de l'utilisateur
		 */
		System.out.println("*******Données de l'utilisateur*******");
		System.out.println("Nom: "+UserController.activeUser.getName());
		System.out.println("Age: "+UserController.activeUser.getAge());
		System.out.println("Langues:");
		for(String language: UserController.activeUser.getLanguage())
			System.out.println(language);




		/*
		 * Choix de la ville
		 */
		try {
			ArrayList<String>citiesList = POIController.getCitiesNames();
			UserController.setCity(citiesList.get(0));
			
			System.out.println("*******Liste des villes*******");
			for(String cityName: citiesList)
				System.out.println(cityName);	
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Error get city");
		}
		
		/*
		 * Choix d'un itineraire de l'utilisateur
		 */
		try {
			ArrayList<String>itinerariesList = UserController.getActiveUserItinerariesNames();
			ItineraryController.getItinerary(itinerariesList.get(0));
			
			System.out.println("*******Liste des itinéraires*******");
			for(String itineraryName: itinerariesList)
				System.out.println(itineraryName);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Error get itinerary");
		}

		setContentView(R.layout.main);
	}
}