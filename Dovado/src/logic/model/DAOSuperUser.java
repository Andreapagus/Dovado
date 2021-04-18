package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	public boolean addUserToJSON(String email, String username, int partner, String password) {
		JSONParser parser = new JSONParser();
		try {
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userObj = (JSONObject) users;
			JSONArray userArray = (JSONArray) userObj.get("users");
			JSONArray userPref = new JSONArray();
			
			
			if (findSuperUser(email, password)==(null)) {				
				JSONObject newUser = new JSONObject();

				newUser.put("id", userArray.size());
				newUser.put("email", email);
				newUser.put("username", username);
				newUser.put("partner", partner);
				newUser.put("password", password);
				newUser.put("preferences", userPref);
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
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Creiamo un metodo per la modifica delle preferenze sul tipo di attività collegate ad un utente.
	public boolean updateUserPreferences(SuperUser su) {
		JSONParser parser = new JSONParser();
		ArrayList<String> oldpref = new ArrayList<String>();
		int i,j;
		
		try {
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userRes = (JSONObject) users;
			JSONArray userArray = (JSONArray) userRes.get("users"),preferences = new JSONArray();
			JSONObject result;
			
			//Aggiungo tutte le preferenze al nuovo JSONArray che ho creato.
			preferences.addAll(su.getPreferences());
			
			//Fatto ciò vado a cercare all'interno del JSON il SuperUser che ho come istanza.
			for(i=0;i<userArray.size();i++) {
				result = (JSONObject)userArray.get(i);
				
				Long IDJSON = (Long) result.get("id");
				JSONArray oldpreferences;
				//Se trovato l'utente si pone all'interno dell'attributo "preferences"
				//il nuovo JSONArray appositamente preparato in precedenza.
				if (su.getUserID().equals(IDJSON)) {
					oldpreferences = (JSONArray) result.get("preferences");
					//Si ricostruisce l'arrayList delle preferenze per compararlo con il nuovo
					//che si andrà ad inserire; Se sono uguali si esce restituendo falso.
					//Se vero si procede nel salvataggio.
					for(j=0;j<oldpreferences.size();j++) {
						oldpref.add((String)oldpreferences.get(j));
					}
					
					if(!su.getPreferences().equals(oldpref)) {
						result.put("preferences", preferences);
						
						FileWriter file = new FileWriter("WebContent/user.json");
						file.write(userRes.toString());;
						file.flush();
						file.close();
						
						return true;
					} 
					else return false;
				}
			}			
		} catch(NullPointerException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	//Creiamo una istanza di una classe a partire dall'id
	public SuperUser findSuperUserByID(Long id ){
		JSONParser parser = new JSONParser();
		int i;
		try 
		{
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userRes = (JSONObject) users;
			JSONArray userArray = (JSONArray) userRes.get("users");
			JSONObject result;
			
			for(i=0;i<userArray.size();i++) {
				result = (JSONObject)userArray.get(i);
				
				Long IDJSON = (Long) result.get("id");
				try {
					if (id.equals(IDJSON)) {
						
						//Il return viene modificato in modo da tener conto della ISTANZIAZIONE ANCHE DELLE PREFERENZE dell'utente.
						if((Long)result.get("partner")==1) {
							Partner partner = new Partner((String) result.get("username"),(String) result.get("email"),(Long) result.get("id"));
							partner.setPreferences(((ArrayList<String>)result.get("preferences")));
							return partner;
						}
						User user = new User((String) result.get("username"),(String) result.get("email"),(Long) result.get("id"));
						user.setPreferences(((ArrayList<String>)result.get("preferences")));
						return user;
					}
				} catch(NullPointerException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	//Creiamo istanza di una classe sapendo la mail-----COME SOTTO ANDREBBE SOSTITUITA DA UN METODO CHE RITORNI L'ID
	public SuperUser findSuperUser(String email) {
		return findSuperUser(email,null);
	}
	
	//qui controlliamo che la mail sia uguale, utile per il login
	public SuperUser findSuperUser (String email, String psw) {
		JSONParser parser = new JSONParser();
		int i;
		try 
		{
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userRes = (JSONObject) users;
			JSONArray userArray = (JSONArray) userRes.get("users");
			JSONObject result;

			for(i=0;i<userArray.size();i++) 
			{
				result = (JSONObject)userArray.get(i);
				
				String emailJSON = (String) result.get("email");
				String passwordJSON = (String) result.get("password");
				try {
					if (email.equals(emailJSON)) {
						if(psw==null) {
							System.out.println("PASSWORD NULLAAAAAAA");
						}
						else if (!psw.equals(passwordJSON)) {
							System.out.println("PASSWORD SBAGLIATA");
							return null;
						}
						//Il return viene modificato in modo da tener conto della ISTANZIAZIONE ANCHE DELLE PREFERENZE dell'utente.
						if((Long)result.get("partner")==1) {
							Partner partner = new Partner((String) result.get("username"),(String) result.get("email"),(Long) result.get("id"));
							partner.setPreferences(((ArrayList<String>)result.get("preferences")));
							return partner;
						}
						User user = new User((String) result.get("username"),(String) result.get("email"),(Long) result.get("id"));
						user.setPreferences(((ArrayList<String>)result.get("preferences")));
						return user;					
					}
				} catch(NullPointerException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	//Ritorna l'ID di un elemento----- WORKINPROGRESS
	public SuperUser findSuperUserID (String email, String psw) {
		JSONParser parser = new JSONParser();
		int i;
		try 
		{
			Object users = parser.parse(new FileReader("WebContent/user.json"));
			JSONObject userRes = (JSONObject) users;
			JSONArray userArray = (JSONArray) userRes.get("users");
			JSONObject result;

			for(i=0;i<userArray.size();i++) 
			{
				result = (JSONObject)userArray.get(i);
				
				String emailJSON = (String) result.get("email");
				String passwordJSON = (String) result.get("password");
				try {
					if (email.equals(emailJSON)) {
						if(psw==null) {
							System.out.println("PASSWORD NULLAAAAAAA");
						}
						else if (!psw.equals(passwordJSON)) {
							System.out.println("PASSWORD SBAGLIATA");
							return null;
						}
						//Il return viene modificato in modo da tener conto della ISTANZIAZIONE ANCHE DELLE PREFERENZE dell'utente.
						if((Long)result.get("partner")==1) {
							Partner partner = new Partner((String) result.get("username"),(String) result.get("email"),(Long) result.get("id"));
							partner.setPreferences(((ArrayList<String>)result.get("preferences")));
							return partner;
						}
						User user = new User((String) result.get("username"),(String) result.get("email"),(Long) result.get("id"));
						user.setPreferences(((ArrayList<String>)result.get("preferences")));
						return user;
					}
				} catch(NullPointerException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
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
