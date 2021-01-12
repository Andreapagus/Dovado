/**
 * 
 */
package logic.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import logic.model.Cadence;
import logic.model.DateBean;
import logic.model.Partner;
import logic.model.Place;
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
		
		// creo posto per esempio ma va rivisto assolutamente 
		CreatePlaceController c = new CreatePlaceController(u);
				
		Place p = c.CreatePlace("ciao", null); //<---- va rivisto
		
		//creo bean per la prma attività:
		
		CreateActivityBean erFaciolo = new CreateActivityBean();
		
		erFaciolo.setType(ActivityType.continua);
		
		erFaciolo.setStartHour(9);
		erFaciolo.setStartMinutes(30);
		
		erFaciolo.setEndHour(18);
		erFaciolo.setEndMinutes(0);
		
		
		CreateActivityController c1 = new CreateActivityController(u,erFaciolo);
		c1.createActivity("ciao", p);
		
		System.out.println("utente ha creato attività normale continua, aperta dalle 9:30 alle 18");
		//----------------------------------------------------
		
		//creo bean per la seconda attività:
		
		CreateActivityBean erFaciolo2 = new CreateActivityBean();
		
		erFaciolo2.setType(ActivityType.periodica);
		erFaciolo2.setCadence(Cadence.WEEKLY);
		
		erFaciolo2.setStartDay(12);
		erFaciolo2.setEndDay(12);
		
		erFaciolo2.setStartMonth(1);
		erFaciolo2.setEndMonth(1);
		
		erFaciolo2.setStartYear(2021);
		erFaciolo2.setEndYear(2021);
		
		erFaciolo2.setStartHour(9);
		erFaciolo2.setStartMinutes(30);
		
		erFaciolo2.setEndHour(18);
		erFaciolo2.setEndMinutes(0);
		
		//creo bean per la terza attività
		
		CreateActivityBean erFaciolo3 = new CreateActivityBean();
		
		erFaciolo3.setType(ActivityType.periodica);
		erFaciolo3.setCadence(Cadence.MONTHLY);
		
		erFaciolo3.setStartDay(12);
		erFaciolo3.setEndDay(15);
		
		erFaciolo3.setStartMonth(1);
		erFaciolo3.setEndMonth(1);
		
		erFaciolo3.setStartYear(2021);
		erFaciolo3.setEndYear(2021);
		
		erFaciolo3.setStartHour(9);
		erFaciolo3.setStartMinutes(30);
		
		erFaciolo3.setEndHour(18);
		erFaciolo3.setEndMinutes(0);
		
		//creo bean per la terza attività
		
		CreateActivityBean erFaciolo4 = new CreateActivityBean();
		
		erFaciolo4.setType(ActivityType.periodica);
		erFaciolo4.setCadence(Cadence.ANNUALLY);
		
		erFaciolo4.setStartDay(12);
		erFaciolo4.setEndDay(15);
		
		erFaciolo4.setStartMonth(1);
		erFaciolo4.setEndMonth(1);
		
		erFaciolo4.setStartYear(2021);
		erFaciolo4.setEndYear(2021);
		
		erFaciolo4.setStartHour(9);
		erFaciolo4.setStartMinutes(30);
		
		erFaciolo4.setEndHour(18);
		erFaciolo4.setEndMinutes(0);
		
		//creo bean per la quinta attività
		
		CreateActivityBean erFaciolo5 = new CreateActivityBean();
				
		erFaciolo5.setType(ActivityType.scadenza);
			
		erFaciolo5.setStartDay(19);
		erFaciolo5.setEndDay(19);
				
		erFaciolo5.setStartMonth(1);
		erFaciolo5.setEndMonth(1);
				
		erFaciolo5.setStartYear(2021);
		erFaciolo5.setEndYear(2021);
				
		erFaciolo5.setStartHour(9);
		erFaciolo5.setStartMinutes(30);
				
		erFaciolo5.setEndHour(18);
		erFaciolo5.setEndMinutes(0);
		
		
		// Sessione 2: un partner crea un'Attività  certificata:
		System.out.println("Creo Partner e attività certificata:");
		
		Partner partner = new Partner("Sessione2");
		CreateActivityController c2 = new CreateActivityController(partner, erFaciolo2);
		c2.createActivity("ciao2",p);
		c2 = new CreateActivityController(partner, erFaciolo3);
		c2.createActivity("ciao3",p);
		c2 = new CreateActivityController(partner, erFaciolo4);
		c2.createActivity("ciao4",p);
		c2 = new CreateActivityController(partner, erFaciolo5);
		c2.createActivity("ciao4",p);
		
		
		System.out.println("Partner ha creato 3 attività certificate:\n2 -periodica settimanale che si ripete ogni martedì \n3- una mensile che va dal 12 al 15\n4- una annuale che si svolfe dal 12/01 al 15/01\n5 - un'attività a scadenza valida solo il 19 feb 2021 ");
		//----------------------------------------------------------
		
		
		/*Sessione 1: l'untente partecipa prima ad un'attività normale
		e poi ad una certificata, mostrando come la sua valuta in game NOn aumenta con la prima,
		Ma lo fa con la seconda*/
		
		FindActivityController cf = new FindActivityController();
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
		
		AddActivityToScheduleController cs = new AddActivityToScheduleController(u,faciolo);
		
		cs.addActivityToSchedule(cf.findActivity(1));
		
		System.out.println(u.getSchedule());
		//----- FINE TEST ADD ACTIVITY TO SCHEDULE------------------------------------------------
		
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 1 fattibile tutti i giorni dalle 9:30 alle 18 sia fattibile oggi alle 19:17");
		if(cf.findActivity(0).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,12), LocalTime.of(19, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 2 fattibile ogni Martedì dalle  9:30 alle 18 sia fattibile oggi (che è martedì) alle 10:17 ...");
		if(cf.findActivity(1).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,12), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 2 fattibile ogni Martedì dalle  9:30 alle 18 sia fattibile domani (che è mercoledì miei dudi) alle 10:17 ...");
		if(cf.findActivity(1).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,13), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 2 fattibile ogni Martedì dalle  9:30 alle 18 sia fattibile il 15 giugno 2021 (che è martedì) alle 10:17 ...");
		if(cf.findActivity(1).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,15), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
	
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 3 fattibile ogni mese dal 12 al 15 dalle  9:30 alle 18 sia fattibile il 15 giugno 2021 alle 10:17 ...");
		if(cf.findActivity(2).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,15), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 3 fattibile ogni mese dal 12 al 15 dalle  9:30 alle 18 sia fattibile il 17 giugno 2021 alle 10:17 ...");
		if(cf.findActivity(2).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,17), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 4 fattibile ogni anno dal 12/01 al 15/01 dalle  9:30 alle 18 sia fattibile il 17 giugno 2021 alle 10:17 ...");
		if(cf.findActivity(3).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,17), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 4 fattibile ogni anno dal 12/01 al 15/01 dalle  9:30 alle 18 sia fattibile il 13 gennaio 2022 alle 10:17 ...");
		if(cf.findActivity(3).playableOnThisDate(LocalDateTime.of(LocalDate.of(2022, 1,13), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 5 fattibile solo il 19/01/2020 dalle  9:30 alle 18 sia fattibile il 13 gennaio 2022 alle 10:17 ...");
		if(cf.findActivity(4).playableOnThisDate(LocalDateTime.of(LocalDate.of(2022, 1,13), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attività 5 fattibile solo il 19/01/2020 dalle  9:30 alle 18 sia fattibile il 19 gennaio 2022 alle 10:17 ...");
		if(cf.findActivity(4).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,19), LocalTime.of(10, 17)))) System.out.println("è fattibile :)");
		else System.out.println("non è fattibile :(");
		//--------------------------------------------------------------
	
	}

}
