package logic.controller;
import java.util.ArrayList;

import logic.model.ActivityVector;
import logic.model.DateBean;
import logic.model.Factory;
import logic.model.Partner;
import logic.model.Place;
import logic.model.PlaceVector;
import logic.model.SuperUser;
import logic.model.User;

public class CreateActivityController {
	private SuperUser u;
	PlaceVector placeVector = PlaceVector.getPlaceVector();
	CreateActivityBean bean;
	
	
	
	CreateActivityController(SuperUser u, CreateActivityBean bean){
		this.u= u;
		this.bean = bean;
	}
	
	public void createActivity(String n, Place p) {
		//spaghetti code here!!!
		switch(bean.getType()) {
		case continua:
			{	
				if(u instanceof User) Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime());
				else Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime() );
			}
		break;
		case periodica:
			{
				if(u instanceof User) Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate(), bean.getCadence());
				else Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate(), bean.getCadence());
			}
		break;
		case scadenza:
			{
				if(u instanceof User) Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate());
				else Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate());
			}
		break;
		}
		
	}
	
	public ArrayList<Place> getPlaces(){
		return placeVector.getPlaces();
	}
	

}
