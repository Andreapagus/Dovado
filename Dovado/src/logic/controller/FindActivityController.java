package logic.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.Cadence;
import logic.model.CertifiedActivity;
import logic.model.DAOSuperUser;
import logic.model.NormalActivity;
import logic.model.Place;
import logic.model.SuperActivity;

public class FindActivityController {
	
	DAOSuperUser daoSU;
	
	//Il metodo prende in ingresso un posto e un intero che rappresenta l'id dell'attivit� che si vuole
	//trovare.
	public SuperActivity findActivityByID (Place p, int n) {
		JSONParser parser = new JSONParser();
		int i,j;
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;
			daoSU = DAOSuperUser.getInstance();
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject)placeArray.get(i);
				
				//Dovr� essere modificato con un id identificativo per le activities.
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");

				if (p.getName().equals(namePrint) && p.getCity().equals(cityPrint) && p.getRegion().equals(regionPrint)) {
						JSONArray activities = (JSONArray) result.get("activities");
						
						//Se non ci sono attivit� nel posto c'� poco da cercare.
						if(activities.size()==0)
							return null;
						
						JSONObject activity,activityJSON;
						
						for(j=0;j<activities.size();j++) {
							
							activity = (JSONObject) activities.get(j);
							
							if(((Long)activity.get("id")).intValue()==n) {
								//Si controlla se certificata o no l'attivit�, passato il test si controlla anche che tipo di attivit� ricorrente sia
								
								Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
								JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
								JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
								
								activityJSON = (JSONObject)activityArray.get(n);
								
								if(((String)activityJSON.get("certified")).equals("yes")) {			
									
									//Se startdate � nulla allora l'attivit� sar� chiaramente un'attivit� Continuos.
									if((activityJSON.get("startdate"))==null)
										return new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")));

									//Se cadence � nulla allora l'attivit� sar� chiaramente un'attivit� Expiring.
									if((activityJSON.get("cadence"))==null)
										return new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p, LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")), LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")));

									//A seguito dei check si capisce che l'attivit� sar� Periodica.
									return new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")),Cadence.valueOf((String)activityJSON.get("cadence")));
								}
								else {	
									
									//Se startdate � nulla allora l'attivit� sar� chiaramente un'attivit� Continuos.
									if((activityJSON.get("startdate"))==null)
										return new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")));

									//Se cadence � nulla allora l'attivit� sar� chiaramente un'attivit� Expiring.
									if((activityJSON.get("cadence"))==null)
										return new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")));
									
									//A seguito dei check si capisce che l'attivit� sar� Periodica.
									return new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")),Cadence.valueOf((String)activityJSON.get("cadence")));
								}
							}
						}
						j=0;
					}
			} 
		}
		
		catch(NullPointerException e) {
			e.printStackTrace();
			return null;
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
	
	public boolean isInJSON(Place p, String activityName, String creatorUsername) {
		JSONParser parser = new JSONParser();
		int i,j;
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;
			daoSU = DAOSuperUser.getInstance();
			
			Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
			JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
			JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject)placeArray.get(i);
				
				//Dovr� essere modificato con un id identificativo per le activities.
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");

				if (p.getName().equals(namePrint) && p.getCity().equals(cityPrint) && p.getRegion().equals(regionPrint)) {
						JSONArray activities = (JSONArray) result.get("activities");
						
						//Se non ci sono attivit� nel posto c'� poco da cercare.
						if(activities.size()==0)
							return false;
						
						JSONObject activity,activityJSON;
						
						for(j=0;j<activities.size();j++) {
							
							activity = (JSONObject) activities.get(j);
							//Preso l'oggetto activity dal JSON places, scandisco il JSON activities sfruttando il campo
							//id della lista di attivit� nei posti. In questo modo posso vedere quali attivit� appartengono
							//al posto che mi interessa per poi eventualmente vedere se tra le attivit� � gi� presente quella che volevo aggiungere.
							activityJSON = (JSONObject)activityArray.get(((Long)activity.get("id")).intValue());
							
							//Se tra le attivit� che ho scandito con questo ciclo for ho trovato una con nome utente che ha creato l'attivit� e nome dell'attivit�
							//UGUALI a quella che stavo per aggiungere restituisco true, indicando che GIA' � presente l'attivit�.
							if(activityJSON.get("creator").equals(creatorUsername) && activityJSON.get("name").equals(activityName)) {
								return true;
							}
						}
						j=0;
					}
			} 
		}
		
		catch(NullPointerException e) {
			e.printStackTrace();
			return false;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
