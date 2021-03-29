package logic.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.Partner;
import logic.model.Place;
import logic.model.SuperUser;

public class CreatePlaceController {
	private SuperUser usr;
	private Place place;
	
	public CreatePlaceController(SuperUser usr) {
		this.usr = usr;
	}

	// Non serve forse l'id per la creazione di posti, ma basta salvarlo nel JSON per ottenerlo e ricaricarlo ogni volta che lo si mostra all'utente
	// per fare una tabella di posti e/o eventi da far vedere agli utenti.
	
	public Place createPlace(String name,String address,String city,String region, String civico,Partner owner){
		this.place = new Place(name, address,city,region,civico,owner);
		int id = this.addPlaceToDB(address, name, city, region, civico, owner); 
		if(id < 0)
			System.out.println("\n\nPosto già creato precedentemente\n\n");
		this.place.setId(id);
		return this.place;
	}
	
	public Place createPlaceWithoutOwner(String name,String address,String city,String region, String civico){
		this.place = new Place(name, address,city,region,civico,null);
		int id = this.addPlaceToDB(address, name, city, region, civico, null); 
		if(id < 0)
			System.out.println("\n\nPosto già creato precedentemente\n\n");
		this.place.setId(id);
		return this.place;
	}
	
	public Place getPlaceFromJSON(String name, String city, String region) {
		Place placeFound;
		placeFound = DAOPlace.getInstance().findPlaceInJSON(name, city, region);
		if(placeFound!=null)
			return placeFound;
		return null;
	
	}
	
	private int addPlaceToDB(String address, String name, String city, String region,String civico, Partner owner) {
		int result_id = DAOPlace.getInstance().addPlaceToJSON(address, name, city, region, civico, owner);
		if(result_id>0)
			return result_id;
		return -1;
	
	}
	
}
