package logic.controller;

import logic.model.Activity;
import logic.model.DateBean;
import logic.model.Schedule;
import logic.model.User;

/*
 * @Author: sav :---------------------------------------------------------------------------------------------------------------------------------
 * associata la responsabilità di creare scheduled activity a schedule
 * plus l'utente è esonerato dalla gestione creando la classe schedule di mezzo
 * 
 * runtime:
 * 
 *1- controller di playActivity chiama controller di AddActivity to schedule
 * 
 * 	gli consegnerà: attività a cui si fa riferimento e bean contenente la data presa da una gui
 * 
 *2 -il controller tramite il metodo add Activity to schedule chiama il metodo di schedule che permetterà di:
 *
 *		2.1: creare un oggetto di tipo Scheduled Activity
 *		2.2: inserirlo nell'arrayList
 *
 *nota:
 *ha senso inserire uno Schedule piuttosto che delegare il compito all'utente?
 *
 *il motivo principale che mi viene in mente è che questo schedulo di attività va getito e mantenuto, nel caso non esistesse la classe Schedule
 *questo compito spetterebbe alla classe utente, andando ad appesantire il contenuto di quella classe e quindi con il rischio di renderla una classe factotum 
 * 
 * 
 * aggiunto timer in scheduled activity, ora quando una scheduled activity viene creata viene aggiunto un timer
 * 
 * se non viene specificato il timer di reminder viene impostato come timer la data di schedulo!
 * 
 * TODO : pensare a un notification system!
 * ----------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * */

public class AddActivityToScheduleController {
	private User session;
	private DateBean timestamp;
	
	public AddActivityToScheduleController(User usr, DateBean timestamp) {
		session = usr;
		this.timestamp= timestamp;
	}
	
	//questo metodo andrebbe chiamato dal controller di playActivity
	public void addActivityToSchedule(Activity a) {
		Schedule s = session.getSchedule();
		
		s.addActivityToSchedule(a, timestamp.getScheduledTime(), timestamp.getReminderTime());
	
	}
	

}
