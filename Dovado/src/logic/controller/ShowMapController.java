package logic.controller;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ShowMapController {
	
	//L'unica istanza attiva di ShowMapController.
	private static ShowMapController INSTANCE;
	
	private ShowMapController() {
		//Privato perché è un singleton.
	}
	
	public static ShowMapController getInstance() {
		if(INSTANCE==null) {
			ShowMapController instance = new ShowMapController();
		}
		return INSTANCE;
	}
	
	public JSONArray getPlaces() {

		JSONParser parser = new JSONParser();
		try {
		
		JSONObject places = (JSONObject) parser.parse(new FileReader("places.json"));
		JSONArray placeArray = (JSONArray) places.get("places");
		
		return placeArray;
		
		} 
		catch(IOException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
		
		return null;
		
	}
	
	
}
