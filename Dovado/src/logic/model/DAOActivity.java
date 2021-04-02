package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.controller.FindActivityController;

public class DAOActivity {
	
	private static DAOActivity INSTANCE;
	
	private DAOActivity() {
	}
	
	public static DAOActivity getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOActivity();
		return INSTANCE;
	}

	public int addActivityToJSON(Place p, SuperActivity activity, String cert) {
		JSONParser parser = new JSONParser();
		FindActivityController fac = new FindActivityController();
		
		
		int i,id;
		try 
		{
			Object places = parser.parse(new FileReader("WebContent/places.json"));
			JSONObject place = (JSONObject) places;
			JSONArray placeArray = (JSONArray) place.get("places");
			
			Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
			JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
			JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
			
			JSONObject result;
			JSONObject activityToAdd = new JSONObject(),activityIdToAdd = new JSONObject();

			
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

			if(activityArray!=null) {
				activityToAdd.put("id",activityArray.size());
				activityIdToAdd.put("id",activityArray.size());
			} else {
				activityToAdd.put("id",0);
				activityIdToAdd.put("id",0);
			}
			
			id = (int) (activityIdToAdd.get("id"));
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject) placeArray.get(i);
				
				String namePrint = (String) result.get("name");
				String cityPrint = (String) result.get("city");
				String regionPrint = (String) result.get("region");
				
				if (activity.getPlace().getName().equals(namePrint) && activity.getPlace().getCity().equals(cityPrint) && activity.getPlace().getRegion().equals(regionPrint)) {
					
					JSONArray activitiesIdArray = (JSONArray) result.get("activities");
					
					if(fac.isInJSON(p, activity.getName(),activity.getCreator().getUsername()))	//Passando il posto in cui sto aggiungendo l'attività, 
						return -1;	
					activitiesIdArray.add(activityIdToAdd); //Salvo l'id dell'attività al posto di appartenenza.
					result.put("activities", activitiesIdArray);
					
					FileWriter file = new FileWriter("WebContent/places.json");
					file.write(place.toString());
					file.flush();
					file.close();
					
					activityArray.add(activityToAdd);
					
					FileWriter file2 = new FileWriter("WebContent/activities.json");
					file2.write(activitiesJOBJ.toString());
					file2.flush();
					file2.close();
					
					return id;
				}
				
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
			}
		return -1;
	
	}

	public boolean updateActivityJSON(SuperActivity sua) {
		JSONParser parser = new JSONParser();
		FindActivityController fac = new FindActivityController();
		
		int i;
		try 
		{
			Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
			JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
			JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
			
			JSONObject result;

			if(activityArray==null) {
				System.out.println("Non ci sono attività da dover modificare!\n");
				return false;
			}
			
			
			for(i=0;i<activityArray.size();i++){
				
				result = (JSONObject) activityArray.get(i);
				
				if(((Long)result.get("id")).intValue()==sua.getId()) {
				
					result.put("creator", sua.getCreator().getUsername());
					
					FileWriter file = new FileWriter("WebContent/activities.json");
					file.write(activitiesJOBJ.toString());
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
