package logic.controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.ContinuosActivity;
import logic.model.DateBean;
import logic.model.ExpiringActivity;
import logic.model.Factory;
import logic.model.Partner;
import logic.model.PeriodicActivity;
import logic.model.Place;
import logic.model.SuperActivity;
import logic.model.SuperUser;
import logic.model.User;

public class CreateActivityController {
	private SuperUser u;
	CreateActivityBean bean;
		
	CreateActivityController(SuperUser u, CreateActivityBean bean){
		this.u= u;
		this.bean = bean;
	}
	
	public void createActivity(String n, Place p) {
		//spaghetti code here!!!
		SuperActivity newActivity;
		switch(bean.getType()) {
		case continua:
			{	
				if(u instanceof User) {
					
					//A seconda di che tipo di utente è abbiamo un metodo di aggiunta attività al file JSON diverso;
					//se l'utente è normale l'attività non è certificata, mentre se lo è avremo un'attività certificata.
					//è importante nella ricostruzione delle attività ricavate dalla persistenza.
					
					newActivity=Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime());
					addActivityToJSON(p,newActivity,"no");
				}
				else {
					newActivity=Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime() );
					addActivityToJSON(p,newActivity,"yes");	
				}
			
		}
		break;
		case periodica:
			{
				if(u instanceof User) {
					//A seconda di che tipo di utente è abbiamo un metodo di aggiunta attività al file JSON diverso;
					//se l'utente è normale l'attività non è certificata, mentre se lo è avremo un'attività certificata.
					//è importante nella ricostruzione delle attività ricavate dalla persistenza.
					newActivity=Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate(), bean.getCadence());
					addActivityToJSON(p,newActivity,"no");
				}
				else {
					newActivity=Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate(), bean.getCadence());
					addActivityToJSON(p,newActivity,"yes");		
				}
			}
		break;
		case scadenza:
			{
				if(u instanceof User) {
					//A seconda di che tipo di utente è abbiamo un metodo di aggiunta attività al file JSON diverso;
					//se l'utente è normale l'attività non è certificata, mentre se lo è avremo un'attività certificata.
					//è importante nella ricostruzione delle attività ricavate dalla persistenza.
					newActivity=Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate());
					addActivityToJSON(p,newActivity,"no");
				}
				else { 
					newActivity=Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate());
					addActivityToJSON(p,newActivity,"yes");					
				}
			}
		break;
		}
		
	}
		
	private boolean addActivityToJSON(Place p,SuperActivity activity,String cert) {
		JSONParser parser = new JSONParser();
		FindActivityController fac = new FindActivityController();
		
		
		int i;
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			JSONObject result;
			JSONObject activityToAdd = new JSONObject();

			if(fac.findActivity(p, placeArray.size())!=null)
				return false;
			
			activityToAdd.put("name", activity.getName());
			activityToAdd.put("creator", activity.getCreator().getUsername());
			if(activity.getFrequency() instanceof ContinuosActivity) {
				activityToAdd.put("opening", activity.getFrequency().getOpeningTime().toString());
				activityToAdd.put("closing", activity.getFrequency().getClosingTime().toString());
				activityToAdd.put("startdate", null);
				activityToAdd.put("enddate", null);
				activityToAdd.put("cadence", null);
				
			}
			if(activity.getFrequency() instanceof ExpiringActivity) {
				activityToAdd.put("opening", activity.getFrequency().getOpeningTime().toString());
				activityToAdd.put("closing", activity.getFrequency().getClosingTime().toString());
				activityToAdd.put("startdate", (((ExpiringActivity) (activity.getFrequency())).getStartDate().toString()));
				activityToAdd.put("enddate", (((ExpiringActivity) (activity.getFrequency())).getEndDate().toString()));
				activityToAdd.put("cadence", null);
				
			}
			if(activity.getFrequency() instanceof PeriodicActivity) {
				activityToAdd.put("opening", activity.getFrequency().getOpeningTime().toString());
				activityToAdd.put("closing", activity.getFrequency().getClosingTime().toString());
				activityToAdd.put("startdate", (((PeriodicActivity) (activity.getFrequency())).getStartDate().toString()));
				activityToAdd.put("enddate", (((PeriodicActivity) (activity.getFrequency())).getEndDate().toString()));
				activityToAdd.put("cadence", (((PeriodicActivity) (activity.getFrequency())).getCadence().toString()));
				
			}
			activityToAdd.put("certified", cert);
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject) placeArray.get(i);
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");
				
				if (activity.getPlace().getName().equals(namePrint) && activity.getPlace().getCity().equals(cityPrint) && activity.getPlace().getRegion().equals(regionPrint)) {
					
					JSONArray activitiesArray = (JSONArray) result.get("activities");
					activitiesArray.add(activityToAdd);
					result.put("activities", activitiesArray);
					
					FileWriter file = new FileWriter("WebContent/places.json");
					file.write(place.toString());
					file.flush();
					file.close();
					
					return true;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;}
		catch (Exception e) {
			e.printStackTrace();
			return false;
			}
		return false;
	}
	
}
