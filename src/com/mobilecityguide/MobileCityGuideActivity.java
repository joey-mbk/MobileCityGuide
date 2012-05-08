package com.mobilecityguide;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.mobilecityguide.datamappers.CategoryMapper;
import com.mobilecityguide.datamappers.ItineraryMapper;
import com.mobilecityguide.datamappers.POIMapper;
import com.mobilecityguide.controllers.CategoryController;
import com.mobilecityguide.controllers.ItineraryController;
import com.mobilecityguide.controllers.POIController;
import com.mobilecityguide.controllers.UserController;
import com.mobilecityguide.datamappers.UserMapper;
import com.mobilecityguide.models.Category;
import com.mobilecityguide.models.Itinerary;
import com.mobilecityguide.models.POI;
import com.mobilecityguide.models.User;

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
			Itinerary itinerary = ItineraryController.getItinerary(itinerariesList.get(0));

			System.out.println("*******Liste des itinéraires*******");
			for(String itineraryName: itinerariesList)
				System.out.println(itineraryName);

			System.out.println("*******Choix de l'itinéraire*******");
			System.out.println("Titre: "+ItineraryController.getItineraryTitle(itinerary));
			System.out.println("Categorie: "+CategoryController.getCategoryTitle(itinerary.getTheme()));


			System.out.println("*******Liste des POI de l'itinéraire choisi*******");
			for(Entry<Integer, POI> entry : itinerary.getPOIList().entrySet()) {
				int step = entry.getKey();
				POI valeur = entry.getValue();
				System.out.println("Step: "+step+" "+ POIController.getPOIName(valeur));
			}						
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Error get itinerary");
		}		
		
		/*
		 * Ajout d'un nouvel utilisateur
		 */

		User newUser = new User();
		newUser.setName("newUser");
		newUser.setAge("adult");
		ArrayList<String>languages = UserController.getAllLanguages();
		System.out.println("*******Liste des langues*******");
		for(String language: languages)
			System.out.println(language);
		
		ArrayList<String> categoriesList = null;
		try {
			categoriesList = CategoryController.getAllCategoriesTitles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] languagesArray = new String[3];
		languages.toArray(languagesArray);
		
		System.out.println("*******Liste des categories*******");
		for(String category: categoriesList)
			System.out.println(category);
		
		ArrayList<Category> userCategoriesList = new ArrayList<Category>();
		userCategoriesList.add(CategoryController.getCategory(categoriesList.get(0)));
		
		System.out.println("*******Creation d'un nouvel utilisateur*******");	
		UserController.addNewUser(newUser, "newUser","adult", languagesArray, userCategoriesList);
		
		/*
		 * Affichage des données de l'utilisateur
		 */
		System.out.println("Nom: "+UserController.activeUser.getName());
		System.out.println("Age: "+UserController.activeUser.getAge());
		System.out.println("Langues:");
		for(String language: UserController.activeUser.getLanguage())
			System.out.println(language);
		
		ArrayList<String>usersList2 = UserController.getAllUsersNames();
		System.out.println("*******Liste des utilisateurs*******");
		for(String userName: usersList2)
			System.out.println(userName);
		
		UserController.delActiveUser();

		ArrayList<String>usersList3 = UserController.getAllUsersNames();
		System.out.println("*******Liste des utilisateurs*******");
		for(String userName: usersList3)
			System.out.println(userName);
		
		System.out.println("END");
		setContentView(R.layout.main);
	}
}