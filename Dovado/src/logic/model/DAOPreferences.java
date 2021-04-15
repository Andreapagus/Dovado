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
	
	public Long findPreferenceId(String preferenceName){
		JSONParser parser = new JSONParser();
		int i;
		Long id;
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
					id = ((Long)result.get("id"));
					return id;
				}
				
				
			}
			//Se uscito dal ciclo for la preferenza non era presente nella persistenza;
			//Per un possibile uso futuro quindi la si aggiunge; restituendo il suo id.
			
			id = Integer.toUnsignedLong(prefArray.size());
			JSONObject newPref = new JSONObject();
			newPref.put("id", id);
			newPref.put("name", preferenceName);
			prefArray.add(newPref);
			
			FileWriter file = new FileWriter("WebContent/preferences.json");
			file.write(preferenceOBJ.toString());;
			file.flush();
			file.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1L;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
		return id;
	}
}
