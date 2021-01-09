package logic.controller;
import java.util.ArrayList;

import logic.model.ActivityVector;
import logic.model.Factory;
import logic.model.Partner;
import logic.model.Place;
import logic.model.PlaceVector;
import logic.model.SuperUser;
import logic.model.User;

public class CreateActivityController {
	private SuperUser u;
	PlaceVector placeVector = PlaceVector.getPlaceVector();
	
	CreateActivityController(SuperUser u){
		this.u= u;
	}
	
	public void createActivity(String n, int type, Place p) {
		if(u instanceof User) Factory.createNormalActivity(n, u, type, p);
		else Factory.createCertifiedActivity(n, u, type, p);
	}
	
	public void createActivity(String n, int type) {
		/*caso in cui il place non esiste va creato!
		*
		*quindi invoco il controller dell'uc Create place
		*
		*aggiungo il place dentro il singleton di places
		*
		*e uso il place per mettercelo nell'activity!
		*
		*
		*/
		
		CreatePlaceController c = new CreatePlaceController(u);
		
		Place p = c.CreatePlace("ciao", null); //<---- va rivisto
		
		
		if(u instanceof User) Factory.createNormalActivity(n, u, type, p);
		else Factory.createCertifiedActivity(n, u, type, p);
	}
	
	public ArrayList<Place> getPlaces(){
		return placeVector.getPlaces();
	}
	
	
	
	
	public static void main(String args[]) {
		User u = new User("sessione1"); //<---- sessione 1
		Partner p = new Partner("Sessione2"); //<---- sessione 2
		
		CreateActivityController c1 = new CreateActivityController(u); //<---- sessione 1
		CreateActivityController c2 = new CreateActivityController(p); //<---- sessione 2
		
		c1.createActivity("ciao", 1); //<---- sessione 1
		c2.createActivity("ciao2", 1); //<---- sessione 2
		
		//------ roba che si vedrà il controller di playactivity -----
		ActivityVector.getActivityVector().getActivity().get(0).PlayActivity(u); //<---- sessione 1
		ActivityVector.getActivityVector().getActivity().get(1).PlayActivity(u); //<---- sessione 1
		ActivityVector.getActivityVector().getActivity().get(1).PlayActivity(u); //<---- sessione 1
		
		System.out.println(u.getBalance());
		
		System.out.println(PlaceVector.getPlaces());
		
	}
}
