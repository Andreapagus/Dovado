/**
 * 
 */
package logic.controller;

import logic.model.DateBean;
import logic.model.Partner;
import logic.model.User;

/**
 * @author sav
 *	CLASSE CREATA PER TESTARE IL FUNZIONAMENTO DELLE CLASSI DELLE APPLICAZIONI
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//----- INIZIO TEST CREATE ACTIVITY E PLAY ACTIVITY -------------------------------------
		
		//Sessione 1: un user crea un'Attività normale:
		System.out.println("Creo utente e attività normale:");
		
		User u = new User("sessione1");
		CreateActivityController c1 = new CreateActivityController(u);
		c1.createActivity("ciao", 1);
		
		System.out.println("utente ha creato attività normale:");
		//----------------------------------------------------
		
		
		// Sessione 2: un partner crea un'Attività  certificata:
		System.out.println("Creo Partner e attività certificata:");
		
		Partner p = new Partner("Sessione2");
		CreateActivityController c2 = new CreateActivityController(p);
		c2.createActivity("ciao2", 1);
		
		System.out.println("Partner ha creato attività certificata");
		//----------------------------------------------------------
		
		
		/*Sessione 1: l'untente partecipa prima ad un'attività normale
		e poi ad una certificata, mostrando come la sua valuta in game NOn aumenta con la prima,
		Ma lo fa con la seconda*/
		
		ControllerFindActivity cf = new ControllerFindActivity();
		System.out.println("L'utente farà un'attività NON certificata:");
		cf.findActivity(0).PlayActivity(u);
		
		System.out.println("saldo dell'utente dopo attività: "+u.getBalance());
		
		System.out.println("L'utente farà un'attività Certificata:");
		cf.findActivity(1).PlayActivity(u);
		System.out.println("saldo dell'utente dopo attività: "+u.getBalance());
		
		//------------------------------------------------------------
	
		//----- FINE TEST CREATE ACTIVITY E PLAY ACTIVITY-----------------------------------------
		
		
		//----- INIZIO TEST ADD ACTIVITY TO SCHEDULE----------------------------------------------
		
		DateBean faciolo = new DateBean(); // <--- da rivedere di riempirlo se ne occupa la view di play activity
		
		faciolo.setYear(2021);
		faciolo.setMonth(1);
		faciolo.setDay(9);
		
		faciolo.setHour(19);
		faciolo.setMinutes(32);
		
		ControllerAddActivityToSchedule cs = new ControllerAddActivityToSchedule(u,faciolo);
		
		cs.addActivityToSchedule(cf.findActivity(1));
		
		System.out.println(u.getSchedule());
		//----- FINE TEST ADD ACTIVITY TO SCHEDULE------------------------------------------------
		
	}

}
