package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.controller.FindActivityController;

public class DAOActivity {
	
	private static DAOActivity INSTANCE;
	private DAOPlace daoPl;
	
	private DAOActivity() {
	}
	
	public static DAOActivity getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOActivity();
		return INSTANCE;
	}

	public Long addActivityToJSON(Place p, SuperActivity activity, String cert) {
		JSONParser parser = new JSONParser();
		FindActivityController fac = new FindActivityController();
		
		Long id;
		int i;
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
			JSONArray newPreferences = new JSONArray();

			activityToAdd.put("place", p.getId());
			activityToAdd.put("name", activity.getName());
			activityToAdd.put("creator", activity.getCreator().getUserID());
			activityToAdd.put("preferences", newPreferences);
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
				activityToAdd.put("id",Integer.toUnsignedLong(activityArray.size()));
				activityIdToAdd.put("id",Integer.toUnsignedLong(activityArray.size()));
			} else {
				activityToAdd.put("id",0L);
				activityIdToAdd.put("id",0L);
			}
			
			id = ((Long)activityIdToAdd.get("id"));
			
			for(i=0;i<placeArray.size();i++) 
			{
				result = (JSONObject) placeArray.get(i);

				Long idPlace = (Long) result.get("id");
					
				if (activity.getPlace().getId()==(idPlace)) {
					
					JSONArray activitiesIdArray = (JSONArray) result.get("activities");
					
					//Passando il posto in cui sto aggiungendo l'attività, controllo se l'attività che voglio aggiungere è già presente.
					
					if(fac.isInJSON(p, activity.getName(),activity.getCreator().getUserID()))	
						return -1L;	
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
			return -1L;}
		catch (Exception e) {
			e.printStackTrace();
			return -1L;
			}
		return -1L;
	
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
				
				if(((Long)result.get("id"))==sua.getId()) {
					
					result.put("place", sua.getPlace().getId());
					result.put("creator", sua.getCreator().getUserID());
					result.put("preferences", sua.getPreferences());
					result.put("name", sua.getName());
					if(sua.getFrequency() instanceof ContinuosActivity) {
						result.put("opening", sua.getFrequency().getOpeningTime().toString());
						result.put("closing", sua.getFrequency().getClosingTime().toString());
						result.put("startdate", null);
						result.put("enddate", null);
						result.put("cadence", null);
						
					}
					if(sua.getFrequency() instanceof ExpiringActivity) {
						result.put("opening", sua.getFrequency().getOpeningTime().toString());
						result.put("closing", sua.getFrequency().getClosingTime().toString());
						result.put("startdate", (((ExpiringActivity) (sua.getFrequency())).getStartDate().toString()));
						result.put("enddate", (((ExpiringActivity) (sua.getFrequency())).getEndDate().toString()));
						result.put("cadence", null);
						
					}
					if(sua.getFrequency() instanceof PeriodicActivity) {
						result.put("opening", sua.getFrequency().getOpeningTime().toString());
						result.put("closing", sua.getFrequency().getClosingTime().toString());
						result.put("startdate", (((PeriodicActivity) (sua.getFrequency())).getStartDate().toString()));
						result.put("enddate", (((PeriodicActivity) (sua.getFrequency())).getEndDate().toString()));
						result.put("cadence", (((PeriodicActivity) (sua.getFrequency())).getCadence().toString()));
						
					}
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
	
	public boolean updateActivityPreferences(SuperActivity sua) {
		JSONParser parser = new JSONParser();
		ArrayList<String> oldpref = new ArrayList<String>();
		int i,j;
		try 
		{
			Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
			JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
			JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
			JSONArray preferences = new JSONArray();
			
			JSONObject result;

			if(activityArray==null) {
				System.out.println("Non ci sono attività da dover modificare!\n");
				return false;
			}
			
			preferences.addAll(sua.getPreferences());
			
			for(i=0;i<activityArray.size();i++){
				JSONArray oldpreferences;
					
				result = (JSONObject) activityArray.get(i);
				
				if(((Long)result.get("id"))==sua.getId()) {
					
					oldpreferences = (JSONArray) result.get("preferences");
					//Si ricostruisce l'arrayList delle preferenze per compararlo con il nuovo
					//che si andrà ad inserire; Se sono uguali si esce restituendo falso.
					//Se vero si procede nel salvataggio.
					for(j=0;j<oldpreferences.size();j++) {
						oldpref.add((String)oldpreferences.get(j));
					}
					
					if(!sua.getPreferences().equals(oldpref)) {
						result.put("preferences",preferences);

						FileWriter file = new FileWriter("WebContent/activities.json");
						file.write(activitiesJOBJ.toString());
						file.flush();
						file.close();
						
						return true;
					} else return false;
				
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
	
	
	
	public boolean deleteActivityJSON(SuperActivity sua) {
		JSONParser parser = new JSONParser();
		daoPl = DAOPlace.getInstance();
		Place pl;
		
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
				
				if(((Long)result.get("id"))==sua.getId()) {
				
					activityArray.remove(i);
					
					Object places = parser.parse(new FileReader("WebContent/places.json"));
					JSONObject place = (JSONObject) places;
					JSONArray placeArray = (JSONArray) place.get("places");
					JSONObject resultPlace;
					
					pl = daoPl.findPlaceInJSON(sua.getPlace().getName(), sua.getPlace().getCity(), sua.getPlace().getRegion());
					
					resultPlace = (JSONObject) placeArray.get(pl.getId().intValue());
					JSONArray activityInPlace = (JSONArray) resultPlace.get("activities");
					for(i=0;i<activityInPlace.size();i++) {
						
						//Prendo un elemento dell'array di eventi nel posto, estraggo il contenuto corrispondente alla chiave "id";
						//Fatto ciò converto a Long l'oggetto risultante ed infine lo confronto con l'id dell'attività per eliminarlo
						//Anche dall'array di eventi nel json dei places.
						if(((Long)((JSONObject)activityInPlace.get(i)).get("id")==sua.getId())){
							activityInPlace.remove(i);
							System.out.println("L'attività è stata eliminata anche dalla lista dei places.\n");
						}
					}
					
					FileWriter file = new FileWriter("WebContent/activities.json");
					file.write(activitiesJOBJ.toString());
					file.flush();
					file.close();
					
					FileWriter file2 = new FileWriter("WebContent/places.json");
					file2.write(place.toString());;
					file2.flush();
					file2.close();
					
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
	
	public ArrayList<SuperActivity> findActivityByPreference (DAOSuperUser daoSU, String preference){
		ArrayList<SuperActivity> matchingActivities = new ArrayList<SuperActivity>();
		SuperActivity matchingActivity;
		JSONParser parser = new JSONParser();
		DAOPlace daoPl = DAOPlace.getInstance();
		
		int i;
		try 
		{
			//Si parsa il JSON delle attività, si estrae poi l'array di attività in esso contenuto.
			Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
			JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
			JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
			JSONArray preferenceList;
			//Si prepara l'oggetto result, dove contenere attività estratte dall'array activityArray.
			JSONObject result;

			//Se nullo si conclude la ricerca restituendo null per indicarne il fallimento.
			if(activityArray==null) {
				System.out.println("Non ci sono attività da dover cercare!\n");
				return null;
			}
			
			//Si inizia a scandire l'array di attività in cerca di quella che contenga almeno una preferenza che combaci con quella cercata.
			for(i=0;i<activityArray.size();i++){
				
				result = (JSONObject) activityArray.get(i);
				preferenceList = (JSONArray) result.get("preferences");
				
				if(preferenceList.contains(preference.toUpperCase())) {
					
					if(((String)result.get("certified")).equals("yes")) {			
						
						//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
						if((result.get("startdate"))==null) {
							matchingActivity = new CertifiedActivity((String)result.get("name"),daoSU.findSuperUserByID((Long)result.get("creator")), daoPl.findPlaceById((Long)result.get("place")),LocalTime.parse((String)result.get("opening")),LocalTime.parse((String)result.get("closing")));
							matchingActivity.setId(((Long)result.get("id")));
							matchingActivity.setPreferences(((ArrayList<String>)result.get("preferences")));
						}
						//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
						else if((result.get("cadence"))==null) {
							matchingActivity = new CertifiedActivity((String)result.get("name"),daoSU.findSuperUserByID((Long)result.get("creator")), daoPl.findPlaceById((Long)result.get("place")) , LocalTime.parse((String)result.get("opening")),LocalTime.parse((String)result.get("closing")), LocalDate.parse((String)result.get("startdate")), LocalDate.parse((String)result.get("enddate")));
							matchingActivity.setId(((Long)result.get("id")));
							matchingActivity.setPreferences(((ArrayList<String>)result.get("preferences")));
						}
						//A seguito dei check si capisce che l'attività sarà Periodica.
						else {
							matchingActivity = new CertifiedActivity((String)result.get("name"),daoSU.findSuperUserByID((Long)result.get("creator")), daoPl.findPlaceById((Long)result.get("place")) ,LocalTime.parse((String)result.get("opening")),LocalTime.parse((String)result.get("closing")),LocalDate.parse((String)result.get("startdate")), LocalDate.parse((String)result.get("enddate")),Cadence.valueOf((String)result.get("cadence")));
							matchingActivity.setId(((Long)result.get("id")));
							matchingActivity.setPreferences(((ArrayList<String>)result.get("preferences")));
						}
						matchingActivity = (CertifiedActivity) matchingActivity;
					}
					else {	
						
						//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
						if((result.get("startdate"))==null) {
							matchingActivity = new NormalActivity((String)result.get("name"),daoSU.findSuperUserByID((Long)result.get("creator")), daoPl.findPlaceById((Long)result.get("place")) ,LocalTime.parse((String)result.get("opening")),LocalTime.parse((String)result.get("closing")));
							matchingActivity.setId(((Long)result.get("id")));
							matchingActivity.setPreferences(((ArrayList<String>)result.get("preferences")));
						}
						//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
						else if((result.get("cadence"))==null) {
							matchingActivity = new NormalActivity((String)result.get("name"),daoSU.findSuperUserByID((Long)result.get("creator")), daoPl.findPlaceById((Long)result.get("place")) ,LocalTime.parse((String)result.get("opening")),LocalTime.parse((String)result.get("closing")),LocalDate.parse((String)result.get("startdate")), LocalDate.parse((String)result.get("enddate")));
							matchingActivity.setId(((Long)result.get("id")));
							matchingActivity.setPreferences(((ArrayList<String>)result.get("preferences")));
							}
						//A seguito dei check si capisce che l'attività sarà Periodica.
						else {
							matchingActivity = new NormalActivity((String)result.get("name"),daoSU.findSuperUserByID((Long)result.get("creator")), daoPl.findPlaceById((Long)result.get("place")),LocalTime.parse((String)result.get("opening")),LocalTime.parse((String)result.get("closing")),LocalDate.parse((String)result.get("startdate")), LocalDate.parse((String)result.get("enddate")),Cadence.valueOf((String)result.get("cadence")));
							matchingActivity.setId(((Long)result.get("id")));
							matchingActivity.setPreferences(((ArrayList<String>)result.get("preferences")));
						}
						matchingActivity = (NormalActivity) matchingActivity;
					}
					matchingActivities.add(matchingActivity);
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
		return matchingActivities;
	}
	
	public SuperActivity findActivityByID (DAOSuperUser daoSU, Place p, int n){
	SuperActivity resultActivity;
	JSONParser parser = new JSONParser();
	int i,j;
	try 
	{
		Object places = parser.parse(new FileReader("WebContent/places.json"));
		JSONObject place = (JSONObject) places;
		JSONArray placeArray = (JSONArray) place.get("places");
		JSONObject result;
		
		for(i=0;i<placeArray.size();i++) 
		{
			result = (JSONObject)placeArray.get(i);
			
			//Sfruttando l'id del posto si controlla prima quale posto sia quello in cui cercare una particolare attività.
			
			Long idPlace = (Long) result.get("id");
			
			if (p.getId()==idPlace) {
					JSONArray activities = (JSONArray) result.get("activities");
					
					//Se non ci sono attività nel posto c'è poco da cercare.
					if(activities.size()==0)
						return null;
					
					JSONObject activity,activityJSON;
					
					for(j=0;j<activities.size();j++) {
						
						activity = (JSONObject) activities.get(j);
						if(((Long)activity.get("id")).intValue()==n) {
							//Si controlla se certificata o no l'attività, passato il test si controlla anche che tipo di attività ricorrente sia:
							
							Object activitiesParser = parser.parse(new FileReader("WebContent/activities.json"));
							JSONObject activitiesJOBJ = (JSONObject) activitiesParser;
							JSONArray activityArray = (JSONArray) activitiesJOBJ.get("activities");
							
							activityJSON = (JSONObject)activityArray.get(n);
							
							if(((String)activityJSON.get("certified")).equals("yes")) {			
								
								//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
								if((activityJSON.get("startdate"))==null) {
									resultActivity = new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUserByID((Long)activityJSON.get("creator")), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")));
									resultActivity.setId(((Long)activityJSON.get("id")));
									resultActivity.setPreferences(((ArrayList<String>)activityJSON.get("preferences")));
									return resultActivity;
								}
								//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
								if((activityJSON.get("cadence"))==null) {
									resultActivity = new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUserByID((Long)activityJSON.get("creator")), p, LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")), LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")));
									resultActivity.setId(((Long)activityJSON.get("id")));
									resultActivity.setPreferences(((ArrayList<String>)activityJSON.get("preferences")));
									return resultActivity;
								}
								//A seguito dei check si capisce che l'attività sarà Periodica.
								resultActivity = new CertifiedActivity((String)activityJSON.get("name"),daoSU.findSuperUserByID((Long)activityJSON.get("creator")), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")),Cadence.valueOf((String)activityJSON.get("cadence")));
								resultActivity.setId(((Long)activityJSON.get("id")));
								resultActivity.setPreferences(((ArrayList<String>)activityJSON.get("preferences")));
								return resultActivity;
							}
							else {	
								
								//Se startdate è nulla allora l'attività sarà chiaramente un'attività Continuos.
								if((activityJSON.get("startdate"))==null) {
									resultActivity = new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUserByID((Long)activityJSON.get("creator")), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")));
									resultActivity.setId(((Long)activityJSON.get("id")));
									resultActivity.setPreferences(((ArrayList<String>)activityJSON.get("preferences")));
									return resultActivity;
								}
								//Se cadence è nulla allora l'attività sarà chiaramente un'attività Expiring.
								if((activityJSON.get("cadence"))==null) {
									resultActivity = new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUserByID((Long)activityJSON.get("creator")), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")));
									resultActivity.setId(((Long)activityJSON.get("id")));
									resultActivity.setPreferences(((ArrayList<String>)activityJSON.get("preferences")));
									return resultActivity;
									}
								//A seguito dei check si capisce che l'attività sarà Periodica.
									resultActivity = new NormalActivity((String)activityJSON.get("name"),daoSU.findSuperUserByID((Long)activityJSON.get("creator")), p,LocalTime.parse((String)activityJSON.get("opening")),LocalTime.parse((String)activityJSON.get("closing")),LocalDate.parse((String)activityJSON.get("startdate")), LocalDate.parse((String)activityJSON.get("enddate")),Cadence.valueOf((String)activityJSON.get("cadence")));
									resultActivity.setId(((Long)activityJSON.get("id")));
									resultActivity.setPreferences(((ArrayList<String>)activityJSON.get("preferences")));
									return resultActivity;
								}
							}
						}
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
	
	public boolean isInJSON(DAOSuperUser daoSU, Place p, String activityName, Long creatorId) {
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
				
				//Sfruttando l'id del posto si controlla prima quale posto sia quello in cui cercare una particolare attività.
				
				Long idPlace = (Long) result.get("id");
				
				if (p.getId()==(idPlace)) {
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
							if(((Long)(activityJSON.get("creator")))==(creatorId) && (((String)activityJSON.get("name")).toUpperCase()).equals(activityName.toUpperCase())) {
								return true;
							}
						}
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
