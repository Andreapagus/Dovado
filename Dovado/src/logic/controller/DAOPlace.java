package logic.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.Partner;
import logic.model.Place;

public class DAOPlace {
	
	private static DAOPlace INSTANCE;
	
	private DAOPlace() {
	}
	
	public static DAOPlace getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOPlace();
		return INSTANCE;
	}
	
	public Place findPlaceInJSON(String name, String city, String region) {
		JSONParser parser = new JSONParser();
		int i,id;
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;

			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject)placeArray.get(i);
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");
				
				if (name.equals(namePrint) && city.equals(cityPrint) && region.equals(regionPrint)) {
					Place placeFound = new Place(namePrint,(String) result.get("address"),cityPrint,regionPrint,(String) result.get("civico"),(Partner) result.get("owner"));
					placeFound.setId(i);
					return placeFound;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			}
		return null;
	}
	
	
	public int addPlaceToJSON(String address, String name, String city, String region,String civico, Partner owner) {
		int i,id=0;
		JSONParser parser = new JSONParser();
		JSONObject newPlace = new JSONObject();
		JSONArray newPlaceActivities = new JSONArray();
		
		newPlace.put("address", address);
		newPlace.put("civico", civico);
		newPlace.put("name", name);
		newPlace.put("city", city);
		newPlace.put("region", region);
		newPlace.put("activities", newPlaceActivities);
		if(owner!=null)
			newPlace.put("owner", owner.getName());
		else 
			newPlace.put("owner", null); 
		
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject)placeArray.get(i);

				String addressPrint = (String) result.get("address");
				String civicoPrint = (String) result.get("civico");
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");
				String ownerPrint = (String) result.get("owner");			
				
				if(address.equals(addressPrint) && civico.equals(civicoPrint) && name.equals(namePrint) && city.equals(cityPrint) && region.equals(regionPrint))
					return -1;
			}
			id=i;
			newPlace.put("id", id);
			placeArray.add(newPlace);
			
			FileWriter file = new FileWriter("WebContent/places.json");
			file.write(place.toString());;
			file.flush();
			file.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
			}
		return id;
	}
	
}