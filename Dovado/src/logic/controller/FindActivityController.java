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
	
	//Il metodo prende in ingresso un posto e un intero che rappresenta l'id dell'attività che si vuole
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
				
				//Dovrà essere modificato con un id identificativo per le activities.
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");

				if (p.getName().equals(namePrint) && p.getCity().equals(cityPrint) && p.getRegion().equals(regionPrint)) {
						JSONArray activities = (JSONArray) result.get("activities");
						
						//Se non ci sono attività nel posto c'è poco da cercare.
						if(activities.size()==0)
							return null;
						
						JSONObject activity,activityJSON;
						
						for(j=0;j<activities.size();j++) {
							
							activity = (JSONObject) activities.get(j);
							
							if(((Long)activity.get("id")).intValue()==n) {
								//Si controlla se certificata o no l'attività, passato il test si controlla anche che tipo di attività ricorrente sia
								
								Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
								JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
								JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
								
								activityJSON = (JSONObject)activityArray.get(n);
								
								if(((String)activityJSON.get("certified")).equals("yes")) {			
									
									//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
									if((activityJSON.get("startdate"))==null)
										return new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")));

									//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
									if((activityJSON.get("cadence"))==null)
										return new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p, LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")), LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")));

									//A seguito dei check si capisce che l'attività sarà Periodica.
									return new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")),Cadence.valueOf((String)activityJSON.get("cadence")));
								}
								else {	
									
									//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
									if((activityJSON.get("startdate"))==null)
										return new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")));

									//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
									if((activityJSON.get("cadence"))==null)
										return new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUser((String)activityJSON.get("creator"),"ELIMINARE"), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")));
									
									//A seguito dei check si capisce che l'attività sarà Periodica.
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
				
				//Dovrà essere modificato con un id identificativo per le activities.
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");

				if (p.getName().equals(namePrint) && p.getCity().equals(cityPrint) && p.getRegion().equals(regionPrint)) {
						JSONArray activities = (JSONArray) result.get("activities");
						
						//Se non ci sono attività nel posto c'è poco da cercare.
						if(activities.size()==0)
							return false;
						
						JSONObject activity,activityJSON;
						
						for(j=0;j<activities.size();j++) {
							
							activity = (JSONObject) activities.get(j);
							//Preso l'oggetto activity dal JSON places, scandisco il JSON activities sfruttando il campo
							//id della lista di attività nei posti. In questo modo posso vedere quali attività appartengono
							//al posto che mi interessa per poi eventualmente vedere se tra le attività è già presente quella che volevo aggiungere.
							activityJSON = (JSONObject)activityArray.get(((Long)activity.get("id")).intValue());
							
							//Se tra le attività che ho scandito con questo ciclo for ho trovato una con nome utente che ha creato l'attività e nome dell'attività
							//UGUALI a quella che stavo per aggiungere restituisco true, indicando che GIA' è presente l'attività.
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
