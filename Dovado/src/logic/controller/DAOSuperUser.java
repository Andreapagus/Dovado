package logic.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logic.model.Cadence;
import logic.model.CertifiedActivity;
import logic.model.NormalActivity;
import logic.model.Partner;
import logic.model.Place;
import logic.model.SuperActivity;
import logic.model.SuperUser;
import logic.model.User;

public class DAOSuperUser {
	
	private static DAOSuperUser INSTANCE;
	
	private DAOSuperUser() {
		//Si implementa come singleton.
	}
	
	public static DAOSuperUser getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOSuperUser();
		return INSTANCE;
	}
	
	public boolean addUserToJSON(String username, int partner) {
		JSONParser parser = new JSONParser();
		try {
			System.out.println("Working principale = " + System.getProperty("user.dir"));
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userObj = (JSONObject) users;
			JSONArray userArray = (JSONArray) userObj.get("users");
			
			
			if (findSuperUser(username)==(null)) {				
				JSONObject newUser = new JSONObject();

				newUser.put("id", userArray.size());
				newUser.put("username", username);
				newUser.put("partner", partner);
				userArray.add(newUser);

				FileWriter file = new FileWriter("WebContent/user.json");
				file.write(userObj.toString());;
				file.flush();
				file.close();
			}
			
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Sapendo che la tua cartella di Working principale è : " + System.getProperty("user.dir") + " allora crea qui una cartella WebContent e copiaci il file user.json ");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public SuperUser findSuperUser (String username) {
		JSONParser parser = new JSONParser();
		int i;
		try 
		{
			System.out.println("Working principale = " + System.getProperty("user.dir"));
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userRes = (JSONObject) users;
			JSONArray userArray = (JSONArray) userRes.get("users");
			JSONObject result;

			for(i=0;i<userArray.size();i++) 
			{
				result = (JSONObject)userArray.get(i);
				
				String usernameJSON = (String) result.get("username");

				try {
					if (username.equals(usernameJSON)) {
						if((Long)result.get("partner")==1)
							return new Partner(username);
						return new User(username);
					}
				} catch(NullPointerException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("Sapendo che la tua cartella di Working principale è : " + System.getProperty("user.dir") + " allora crea qui una cartella WebContent e copiaci il file user.json ");
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
}
