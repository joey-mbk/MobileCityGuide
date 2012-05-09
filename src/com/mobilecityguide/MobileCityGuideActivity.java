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

		POIController.poiMapper = new POIMapper(this);
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
		System.out.println("*******Liste des categories de l'utilisateur*******");
		for(String category: UserController.getActiveUserCategoriesNames())
			System.out.println(category);




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

			System.out.println("*******Liste des itinéraires*******");
			for(String itineraryName: itinerariesList)
				System.out.println(itineraryName);

			Itinerary itinerary = ItineraryController.getItinerary(itinerariesList.get(0));

			System.out.println("*******Choix de l'itinéraire*******");
			System.out.println("Titre: "+ItineraryController.getItineraryTitle(itinerary));
			System.out.println("Categorie: "+CategoryController.getCategoryTitle(itinerary.getTheme().getId()));


			System.out.println("*******Liste des POI de l'itinéraire choisi*******");
			for(Entry<Integer, POI> entry : itinerary.getPOIList().entrySet()) {
				int step = entry.getKey();
				POI valeur = entry.getValue();
				System.out.println("Step: "+step+" "+ POIController.getPOIName(valeur));
			}
			
			itinerary.reOrder(2, 5);
			//itinerary.delPOI(1);
			System.out.println("*******Liste modifiée des POI de l'itinéraire choisi*******");
			for(Entry<Integer, POI> entry : itinerary.getPOIList().entrySet()) {
				int step = entry.getKey();
				POI valeur = entry.getValue();
				System.out.println("Step: "+step+" "+ POIController.getPOIName(valeur));
			}
			/*System.out.println("*******Création d'un nouvel itinéraire*******");
			Itinerary itinerary2 = new Itinerary();
			itinerary2.addTitle("english", "itinerary10");
			itinerary2.setTheme(UserController.activeUser.getUserCategories().get(0));
			POI[]poiList = POIController.getPOIOfCity();
			int i = 0;
			for(POI poi: poiList){
				itinerary2.addPOI(i, poi);
				i++;
			}
			UserController.addUserItinerary(itinerary2);
			
			ArrayList<String>itinerariesList2 = UserController.getActiveUserItinerariesNames();
			System.out.println("*******Liste des itinéraires2*******");
			for(String itineraryName: itinerariesList2)
				System.out.println(itineraryName);

			System.out.println("*******Choix de l'itinéraire2*******");
			Itinerary itinerary3 = ItineraryController.getItinerary(itinerariesList2.get(itinerariesList2.size()-1));
			System.out.println("Titre: "+ItineraryController.getItineraryTitle(itinerary3));
			System.out.println("Categorie: "+CategoryController.getCategoryTitle(itinerary3.getTheme().getId()));


			System.out.println("*******Liste 2 des POI de l'itinéraire choisi*******");
			for(Entry<Integer, POI> entry : itinerary3.getPOIList().entrySet()) {
				int step = entry.getKey();
				POI valeur = entry.getValue();
				System.out.println("Step: "+step+" "+ POIController.getPOIName(valeur));
			}*/
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Error get itinerary");
		}
	
			/*
			 * Ajout d'un nouvel utilisateur
			 */
			/*
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
		String name : "newUser"
		if(!isUserNameAlreadyUsed(name))	
		UserController.addNewUser(newUser, name,"adult", languagesArray, userCategoriesList);
			 */	
			/*
			 * Affichage des données de l'utilisateur
			 */
			/*	System.out.println("Nom: "+UserController.activeUser.getName());
		System.out.println("Age: "+UserController.activeUser.getAge());
		System.out.println("Langues:");
		for(String language: UserController.activeUser.getLanguage())
			System.out.println(language);
			 */
			/*	
	//	UserController.setActiveUserName("NewUser2");
	//	UserController.saveUser();
	//	UserController.delActiveUser();

		ArrayList<String>usersList3 = UserController.getAllUsersNames();
		System.out.println("*******Liste des utilisateurs*******");
		for(String userName: usersList3)
			System.out.println(userName);
			 */

			/*
			 * Affichage des POI en fonction des centres d'intérêt de l'utilisateur
			 */
			/*	System.out.println("*******Liste des POI en fonction des centres d'intérêt de l'utilisateur*******");
		try {
			for(String POIName: POIController.getPOINamesofUserCategories())
				System.out.println(POIName);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
			/*	
		System.out.println("*******Liste des fetched POI*******");
		for(Entry<Integer, POI> entry : POIController.fetchedPOI.entrySet()) {
			int step = entry.getKey();
			POI valeur = entry.getValue();
			System.out.println("ID: "+step+" "+ POIController.getPOIName(valeur));
		}
		System.out.println("*******Liste des fetched itineraries*******");
		for(Entry<Integer, Itinerary> entry : ItineraryController.fetchedItineraries.entrySet()) {
			int step = entry.getKey();
			Itinerary valeur = entry.getValue();
			System.out.println("ID: "+step+" "+ ItineraryController.getItineraryTitle(valeur));
		}
			 */
			System.out.println("END");
			setContentView(R.layout.main);
		}
	}
	