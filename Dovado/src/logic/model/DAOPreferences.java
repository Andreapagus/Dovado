package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DAOPreferences {
	
	private static DAOPreferences INSTANCE;
	
	private DAOPreferences() {
		//Da vedere se serve singleton.
	}
	
	public static DAOPreferences getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOPreferences();
		return INSTANCE;
	}
	
	public String getPreferenceFromJSON(int placeInJSON) {
		String preference;
		JSONParser parser = new JSONParser();
		int i;
		try 
		{
			Object preferences = parser.parse(new FileReader("WebContent/preferences.json"));
			JSONObject preferenceOBJ = (JSONObject) preferences;
			JSONArray prefArray = (JSONArray) preferenceOBJ.get("preferences");
			JSONObject result;
			
			result = (JSONObject)prefArray.get(placeInJSON);
				
			preference = ((String) result.get("name"));
			
			return preference;
			
			//Se uscito dal ciclo for la preferenza non era presente nella persistenza;
			//Per un possibile uso futuro quindi la si aggiunge; restituendo il suo id.
			
			// POTREMMO VOLERLO CAMBIARE ( SE NON VOGLIAMO IL JSON INTASATO DI SINONIMI DI UNO STESSO
			// INSERITO DIVERSE VOLTE DA PARTE DI UTENTI ) TOGLIENDO L'AGGIUNTA IN AUTOMATICO DELLA PREFERENZA.
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean preferenceIsInJSON(String preferenceName){
		JSONParser parser = new JSONParser();
		int i;
		try 
		{
			Object preferences = parser.parse(new FileReader("WebContent/preferences.json"));
			JSONObject preferenceOBJ = (JSONObject) preferences;
			JSONArray prefArray = (JSONArray) preferenceOBJ.get("preferences");
			JSONObject result;
			preferenceName = preferenceName.toUpperCase();
			
			for(i=0;i<prefArray.size();i++) {
				result = (JSONObject)prefArray.get(i);
				
				String name = ((String) result.get("name")).toUpperCase();
			
				if (preferenceName.equals(name)) {
					return true;
				}
				
				
			}
			//Se uscito dal ciclo for la preferenza non era presente nella persistenza;
			//Per un possibile uso futuro quindi la si aggiunge; restituendo il suo id.
			
			// POTREMMO VOLERLO CAMBIARE ( SE NON VOGLIAMO IL JSON INTASATO DI SINONIMI DI UNO STESSO
			// INSERITO DIVERSE VOLTE DA PARTE DI UTENTI ) TOGLIENDO L'AGGIUNTA IN AUTOMATICO DELLA PREFERENZA.
			JSONObject newPref = new JSONObject();
			newPref.put("name", preferenceName);
			prefArray.add(newPref);
			
			FileWriter file = new FileWriter("WebContent/preferences.json");
			file.write(preferenceOBJ.toString());;
			file.flush();
			file.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
