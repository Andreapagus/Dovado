package logic.controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.ContinuosActivity;
import logic.model.DAOActivity;
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
	private DAOActivity daoAc;
	CreateActivityBean bean;
		
	CreateActivityController(SuperUser u, CreateActivityBean bean){
		this.u= u;
		this.bean = bean;
	}
	
	public void createActivity(String n, Place p) {
		//spaghetti code here!!!
		int id;
		daoAc = DAOActivity.getInstance();
		SuperActivity newActivity;
		switch(bean.getType()) {
		case continua:
			{	
				if(u instanceof User) {
					
					//A seconda di che tipo di utente è abbiamo un metodo di aggiunta attività al file JSON diverso;
					//se l'utente è normale l'attività non è certificata, mentre se lo è avremo un'attività certificata.
					//è importante nella ricostruzione delle attività ricavate dalla persistenza.
					
					newActivity=Factory.createNormalActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime());
					id = daoAc.addActivityToJSON(p,newActivity,"no");
					newActivity.setId(id);
				}
				else {
					newActivity=Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime() );
					id = daoAc.addActivityToJSON(p,newActivity,"yes");	
					newActivity.setId(id);
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
					id = daoAc.addActivityToJSON(p,newActivity,"no");
					newActivity.setId(id);
				}
				else {
					newActivity=Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate(), bean.getCadence());
					id = daoAc.addActivityToJSON(p,newActivity,"yes");		
					newActivity.setId(id);
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
					id = daoAc.addActivityToJSON(p,newActivity,"no");
					newActivity.setId(id);
				}
				else { 
					newActivity=Factory.createCertifiedActivity(n, u, p, bean.getOpeningTime(), bean.getClosingTime(), bean.getStartDate(), bean.getEndDate());
					id = daoAc.addActivityToJSON(p,newActivity,"yes");	
					newActivity.setId(id);				
				}
			}
		break;
		}
		
	}
	
}
