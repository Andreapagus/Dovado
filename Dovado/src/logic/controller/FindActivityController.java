package logic.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.Activity;
import logic.model.Cadence;
import logic.model.CertifiedActivity;
import logic.model.NormalActivity;
import logic.model.Partner;
import logic.model.Place;
import logic.model.SuperActivity;
import logic.model.SuperUser;

public class FindActivityController {
	
	DAOSuperUser daoSU;
	
	public SuperActivity findActivity (Place p, int n) {
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
						JSONObject activity = (JSONObject) activities.get(0);
							
						for(j=0;j<activities.size();j++) {
							if(j==n) {
								activity = (JSONObject) activities.get(j);
								
								//Si controlla se certificata o no l'attività, passato il test si controlla anche che tipo di attività ricorrente sia
								
								if(((String)activity.get("certified")).equals("yes")) {			
									
									//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
									if((activity.get("startdate"))==null)
										return new CertifiedActivity((String)activity.get("name"),daoSU.findSuperUser((String)activity.get("creator")), p,LocalTime.parse((String)activity.get("opening")),LocalTime.parse((String)activity.get("closing")));

									//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
									if((activity.get("cadence"))==null)
										return new CertifiedActivity((String)activity.get("name"),daoSU.findSuperUser((String)activity.get("creator")), p, LocalTime.parse((String)activity.get("opening")),LocalTime.parse((String)activity.get("closing")), LocalDate.parse((String)activity.get("startdate")), LocalDate.parse((String)activity.get("enddate")));

									//A seguito dei check si capisce che l'attività sarà Periodica.
									return new CertifiedActivity((String)activity.get("name"),daoSU.findSuperUser((String)activity.get("creator")), p,LocalTime.parse((String)activity.get("opening")),LocalTime.parse((String)activity.get("closing")),LocalDate.parse((String)activity.get("startdate")), LocalDate.parse((String)activity.get("enddate")),Cadence.valueOf((String)activity.get("cadence")));
								}
								else {	
									
									//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
									if((activity.get("startdate"))==null)
										return new NormalActivity((String)activity.get("name"),daoSU.findSuperUser((String)activity.get("creator")), p,LocalTime.parse((String)activity.get("opening")),LocalTime.parse((String)activity.get("closing")));

									//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
									if((activity.get("cadence"))==null)
										return new NormalActivity((String)activity.get("name"),daoSU.findSuperUser((String)activity.get("creator")), p,LocalTime.parse((String)activity.get("opening")),LocalTime.parse((String)activity.get("closing")),LocalDate.parse((String)activity.get("startdate")), LocalDate.parse((String)activity.get("enddate")));
									
									//A seguito dei check si capisce che l'attività sarà Periodica.
									return new NormalActivity((String)activity.get("name"),daoSU.findSuperUser((String)activity.get("creator")), p,LocalTime.parse((String)activity.get("opening")),LocalTime.parse((String)activity.get("closing")),LocalDate.parse((String)activity.get("startdate")), LocalDate.parse((String)activity.get("enddate")),Cadence.valueOf((String)activity.get("cadence")));
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
}
