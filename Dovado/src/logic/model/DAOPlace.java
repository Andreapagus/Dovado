package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DAOPlace {
	
	private static DAOPlace INSTANCE;
	private DAOSuperUser daoSu;
	
	private DAOPlace() {
	}
	
	public static DAOPlace getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOPlace();
		return INSTANCE;
	}

	//Aggiunto un metodo per trovare un posto tramite ID, utile durante la reistanzazione da persistenza delle attività.
	public Place findPlaceById(Long id) {
		JSONParser parser = new JSONParser();
		int i;
		daoSu = DAOSuperUser.getInstance();
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;

			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject)placeArray.get(i);
				
				Long idJSON = (Long) result.get("id");
				
				if (idJSON.equals(id)) {
					Place placeFound = new Place((String)result.get("name"),(String) result.get("address"),(String)result.get("city"),(String)result.get("region"),(String) result.get("civico"),(Partner) daoSu.findSuperUserByID((Long)result.get("owner")));
					placeFound.setId((Long) result.get("id"));
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
	
	public Place findPlaceInJSON(String name, String city, String region) {
		JSONParser parser = new JSONParser();
		int i;
		daoSu = DAOSuperUser.getInstance();
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
					Place placeFound = new Place(namePrint,(String) result.get("address"),cityPrint,regionPrint,(String) result.get("civico"),(Partner) daoSu.findSuperUserByID((Long)result.get("owner")));
					placeFound.setId((Long) result.get("id"));
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
			newPlace.put("owner", owner.getUserID());
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
				
				if(address.equals(addressPrint) && civico.equals(civicoPrint) && name.equals(namePrint) && city.equals(cityPrint) && region.equals(regionPrint))
					return ((Long) result.get("id")).intValue();
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

	//Aggiunto per permettere la modifica di qualsiasi attributo in Place
	//salvandola anche in persistenza.
	public boolean updatePlaceJSON(Place p) {
		
		int i;
		JSONParser parser = new JSONParser();
		
		try {
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject)placeArray.get(i);

				Long placeId = ((Long) result.get("id"));
				
				/*--------------------PRINT DI CONTROLLO-----------------------------------------------*/
				System.out.println("Controllo di comparazione ID posti:");
				System.out.println(p.getId()==(placeId));
				System.out.println(p.getOwner().getUserID());
				/*--------------------PRINT DI CONTROLLO-----------------------------------------------*/				
				
				if(p.getId()==(placeId)) {
					//Salvo nella persistenza il proprietario con il suo nome. In futuro lo si potrebbe salvare in base all'id.
					result.put("owner", p.getOwner().getUserID());
					placeArray.set(i, result);
					break;
				}
			}
			FileWriter file = new FileWriter("WebContent/places.json");
			file.write(place.toString());;
			file.flush();
			file.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;}
		catch (Exception e) {
			e.printStackTrace();
			return false;
			}
		return true;
	}

	
}