/**
 * 
 */
package logic.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import logic.model.Activity;
import logic.model.Cadence;
import logic.model.CertifiedActivity;
import logic.model.DAOSuperUser;
import logic.model.DateBean;
import logic.model.Partner;
import logic.model.Place;
import logic.model.PlaceBean;
import logic.model.SuperActivity;
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
		
		//scrivo per cambiare
		
		
		
		//Sessione 1: un user crea un'Attivit√† normale:
		System.out.println("Creo utente e attivit√† normale:\n");
		
		/* 				User u = new User("sessione1"); 					*/
		DAOSuperUser  dao = DAOSuperUser.getInstance();
		CreateUserController createUser= new CreateUserController();
		int control =  createUser.createUser("sessione1@drew.com","sessione1","sessione1");
		if (control != 1) {
			System.out.println("Errore nella creazione dell'utente");
			return;
		}
		User u = (User)  dao.findSuperUser("sessione1@drew.com","sessione1");
		
		if (u == null ) {
			System.out.println("USER NULL");}
		// creo posto per esempio ma va rivisto assolutamente 
		PlaceBean placeBean = PlaceBean.getInstance();
		
		//Crea un posto senza ancora specificarne il proprietario; nella PlaceBean si salva anche su un file JSON il posto risultante in modo da mantenerlo in memoria.
		//Inoltre sar‡ salvato su PlaceVector; un posto richieder‡ come attributi la via, il nome, la citt‡, il numero civico, la regione ed infine il proprietario.
		System.out.println("Creo un posto, lo salvo nel file json:\n");
		
		//Aggiungo un posto utilizzando il bean PlaceBean; questo a sua volta chiamer‡ CreatePlaceController per fornire il posto creato
		//e salvarlo nel DB.
		Place p = placeBean.addPlace("Concerto di Tupac","Via nicola salvi","Roma","Lazio","65",null,u); 
		
		//creo bean per la prma attivit√†:
		
		CreateActivityBean erFaciolo = new CreateActivityBean();
		
		erFaciolo.setType(ActivityType.continua);
		
		erFaciolo.setStartHour(9);
		erFaciolo.setStartMinutes(30);
		
		erFaciolo.setEndHour(18);
		erFaciolo.setEndMinutes(0);
		
		
		CreateActivityController c1 = new CreateActivityController(u,erFaciolo);
		c1.createActivity("ciao", p);
		
		System.out.println("\n---------------------utente ha creato attivit√† normale continua, aperta dalle 9:30 alle 18---------------------------\n");
		//----------------------------------------------------
		
		//creo bean per la seconda attivit√†:
		
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
		
		//creo bean per la terza attivit√†
		
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
		
		//creo bean per la terza attivit√†
		
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
		
		//creo bean per la quinta attivit√†
		
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
		
		
		// Sessione 2: un partner crea un'Attivit√†  certificata:
		System.out.println("Creo Partner e attivit√† certificata:\n");
		
		/*									Partner partner = new Partner("Sessione2");											*/
		
		control = createUser.createPartner("Sessione2@drew.com","Sessione2","Sessione2");
		if (control != 1) {
			System.out.println("Errore nella creazione dell'utente");
			return;
		}
		
		Partner partner = (Partner) dao.findSuperUserByID(Long.valueOf(2));
		
		//Partner partner = (Partner) dao.findSuperUser("Sessione2@drew.com","Sessione2");
		if (partner == null) {
			System.out.println("PARTNER NULLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		} else {
			System.out.println(" IL PARTNER HA QUESTI ATTRIBUTI: "+partner.getEmail()+" "+partner.getUsername()+" "+partner.getName()+"\n");
		}
		CreateActivityController c2 = new CreateActivityController(partner, erFaciolo2);
		c2.createActivity("ciao2",p);
		c2 = new CreateActivityController(partner, erFaciolo3);
		c2.createActivity("ciao3",p);
		c2 = new CreateActivityController(partner, erFaciolo4);
		c2.createActivity("ciao4",p);
		c2 = new CreateActivityController(partner, erFaciolo5);
		c2.createActivity("ciao5",p);
		
		
		System.out.println("Partner ha creato 3 attivit‡† certificate:\n2 -periodica settimanale che si ripete ogni martedÏ \n3- una mensile che va dal 12 al 15\n4- una annuale che si svolge dal 12/01 al 15/01\n5 - un'attivit‡† a scadenza valida solo il 19 feb 2021 ");
		//----------------------------------------------------------
		
		
		/*Sessione 1: l'untente partecipa prima ad un'attivit√† normale
		e poi ad una certificata, mostrando come la sua valuta in game NOn aumenta con la prima,
		Ma lo fa con la seconda*/
		
		FindActivityController cf = new FindActivityController();
		SuperActivity activityFound;
		CertifiedActivity activityIsCertified;
		System.out.println("\nL'utente far‡† un'attivit‡† NON certificata:\n");
		
		//Tramite find activity trovo la prima attivit‡ del posto p, poi ottenuta l'attivit‡ con un metodo generico che restituisce SuperActivity
		//Controllo che tipo di istanza sia e a seconda di questo restituisco o meno il reward.
		
		activityFound = cf.findActivityByID(p,0);
		if(activityFound instanceof CertifiedActivity) {
			activityIsCertified = (CertifiedActivity)activityFound;
			activityIsCertified.PlayActivity(u);
		}
		
		System.out.println("saldo dell'utente dopo attivit‡†: "+u.getBalance());
		
		System.out.println("\nL'utente far‡† un'attivit‡† Certificata:");
		
		activityFound = cf.findActivityByID(p,1);
		if(activityFound instanceof CertifiedActivity) {
			activityIsCertified = (CertifiedActivity)activityFound;
			activityIsCertified.PlayActivity(u);
		}
		
		System.out.println("\nsaldo dell'utente dopo attivit‡†: "+u.getBalance());
		
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
		
		cs.addActivityToSchedule((Activity)cf.findActivityByID(p,1));
		
		System.out.println(u.getSchedule()+"\n");
		//----- FINE TEST ADD ACTIVITY TO SCHEDULE------------------------------------------------
		
		try {
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 1 fattibile tutti i giorni dalle 9:30 alle 18 sia fattibile oggi alle 19:17 ...\nId del posto: "+p.getId());
		if(cf.findActivityByID(p,0).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,12), LocalTime.of(19, 17)))) System.out.println("√® fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 2 fattibile ogni MartedÏ dalle  9:30 alle 18 sia fattibile oggi (che Ë martedÏ) alle 10:17 ...");
		if(cf.findActivityByID(p,1).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,12), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 2 fattibile ogni MartedÏ dalle  9:30 alle 18 sia fattibile domani (che Ë mercoledÏ miei dudi) alle 10:17 ...");
		if(cf.findActivityByID(p,1).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,13), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 2 fattibile ogni MartedÏ dalle  9:30 alle 18 sia fattibile il 15 giugno 2021 (che Ë martedÏ) alle 10:17 ...");
		if(cf.findActivityByID(p,1).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,15), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
	
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 3 fattibile ogni mese dal 12 al 15 dalle  9:30 alle 18 sia fattibile il 15 giugno 2021 alle 10:17 ...");
		if(cf.findActivityByID(p,2).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,15), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 3 fattibile ogni mese dal 12 al 15 dalle  9:30 alle 18 sia fattibile il 17 giugno 2021 alle 10:17 ...");
		if(cf.findActivityByID(p,2).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,17), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 4 fattibile ogni anno dal 12/01 al 15/01 dalle  9:30 alle 18 sia fattibile il 17 giugno 2021 alle 10:17 ...");
		if(cf.findActivityByID(p,3).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 6,17), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 4 fattibile ogni anno dal 12/01 al 15/01 dalle  9:30 alle 18 sia fattibile il 13 gennaio 2022 alle 10:17 ...");
		if(cf.findActivityByID(p,3).playableOnThisDate(LocalDateTime.of(LocalDate.of(2022, 1,13), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 5 fattibile solo il 19/01/2020 dalle  9:30 alle 18 sia fattibile il 13 gennaio 2022 alle 10:17 ...");
		if(cf.findActivityByID(p,4).playableOnThisDate(LocalDateTime.of(LocalDate.of(2022, 1,13), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		
		//----------------------------------------------------------------
		System.out.println("controllo che attivit‡† 5 fattibile solo il 19/01/2020 dalle  9:30 alle 18 sia fattibile il 19 gennaio 2022 alle 10:17 ...");
		if(cf.findActivityByID(p,4).playableOnThisDate(LocalDateTime.of(LocalDate.of(2021, 1,19), LocalTime.of(10, 17)))) System.out.println("Ë fattibile :)\n");
		else System.out.println("non Ë fattibile :(\n");
		//--------------------------------------------------------------
		} catch (NullPointerException e) {
			System.out.println("\nUna delle attivit‡ non Ë stata trovata nel file JSON\n");
			e.printStackTrace();
		}
		
		//----------------TEST CHANNELL----------------------
		System.out.println("\n"+ "------------------------------TEST CHANNELL-------------------------------------------"+"\n");
		
		PlayActivityController cpa = new PlayActivityController((Activity)cf.findActivityByID(p,3));
		
		cpa.readOnChannell(0);
		
		cpa.writeOnChannell(1, "Io sono pane");
		cpa.writeOnChannell(2, "Tu sei pane");
		cpa.writeOnChannell(0, "Tupac Ë pane");
		
		cpa.writeOnChannell(1, "Noi siamo pane");
		cpa.writeOnChannell(2, "Voi siete pane");
		cpa.writeOnChannell(0, "I chad sono chad");
		
		cpa.readOnChannell(1);
		
		ClaimAPlaceController cap = new ClaimAPlaceController();
		
		System.out.println("\n-------------------------------------------Aggiunta Propietario a posto gi‡ esistente------------------------------------------------\n");
		if(cap.claimPlaceOwnership(partner, p))
			System.out.println("Operazione effettuata con successo!");
		else System.out.println("Operazione di aggiunta proprietario del posto fallita!");
		
		ClaimActivityController ca = new ClaimActivityController();
		
		System.out.println("\n-------------------------------------------Aggiunta Propietario a attivit‡ gi‡ esistente---------------------------------------------\n");
		if(ca.claimActivityOwnership(partner, cf.findActivityByID(p,0)))
			System.out.println("Operazione effettuata con successo!");
		else System.out.println("Operazione di aggiunta proprietario dell'attivit‡ fallita!");
		
		System.out.println("\n-------------------------------------------Eliminazione di attivit‡ dalla persistenza------------------------------------------------\n");	
		
		ManageActivityController mac = new ManageActivityController();
		SuperActivity sua = cf.findActivityByID(p, 4),modifiedsua;
		System.out.println("Nome attivit‡ precedente alla modifica: "+sua.getName()+" L'id "+sua.getId()+" anche il creatore per vedere una cosa: "+sua.getCreator().getUserID());
		modifiedsua = mac.setActivityName(sua,"NomeDeProva");
		System.out.println("Nome attivit‡ a seguito della modifica: "+modifiedsua.getName());
		
		System.out.println("\n-------------------------------------------Eliminazione di attivit‡ dalla persistenza------------------------------------------------\n");	
		CreateActivityBean erFaciolo6 = new CreateActivityBean();
		
		erFaciolo6.setType(ActivityType.scadenza);
			
		erFaciolo6.setStartDay(19);
		erFaciolo6.setEndDay(19);
				
		erFaciolo6.setStartMonth(1);
		erFaciolo6.setEndMonth(1);
				
		erFaciolo6.setStartYear(2021);
		erFaciolo6.setEndYear(2021);
				
		erFaciolo6.setStartHour(9);
		erFaciolo6.setStartMinutes(30);
				
		erFaciolo6.setEndHour(18);
		erFaciolo6.setEndMinutes(0);
		
		c2 = new CreateActivityController(partner, erFaciolo6);
		c2.createActivity("anvedime",p);
		
		System.out.println(cf.findActivityByID(p, 6)!=null);	
		
		
		/*if(mac.deleteActivity(cf.findActivityByID(p, 6)))
			System.out.println("Eliminazione effettuata con successo.");	
		else System.out.println("Eliminazione NON AVVENUTA.");	
		*/
		System.out.println("\n-------------------------------------------Cambiamento frequenza di attivit‡ nella persistenza e fuori--------------------------------\n");	
		
		//PER TESTARE UN METODO DI INTERESSE BASTA COMMENTARE GLI ALTRI DUE.
		
		System.out.println("\n-------------------------------------------Cambiamento ad attivit‡ CONTINUOS----------------------------------------------------------\n");	
		if((modifiedsua = mac.setFrequency(sua, LocalTime.of(0,0), LocalTime.of(23,30), LocalDate.of(2039, 1, 18), LocalDate.of(2042, 1, 18)))!=null)
			System.out.println("Frequenza settata a CONTINUOS con successo.");	
		else System.out.println("Frequenza NON SETTATA CORRETTAMENTE.");	
		
		System.out.println("\n-------------------------------------------Cambiamento ad attivit‡ EXPIRING-----------------------------------------------------------\n");
		if((modifiedsua = mac.setFrequency(sua, LocalTime.of(0,0), LocalTime.of(23,30)))!=null)
			System.out.println("Frequenza settata a EXPIRING con successo.");	
		else System.out.println("Frequenza NON SETTATA CORRETTAMENTE.");	
		
		System.out.println("\n-------------------------------------------Cambiamento ad attivit‡ PERIODIC----------------------------------------------------------\n");
		if((modifiedsua = mac.setFrequency(sua, LocalTime.of(0,0), LocalTime.of(23,30), LocalDate.of(2039, 1, 18), LocalDate.of(2042, 1, 18),Cadence.ANNUALLY))!=null)
			System.out.println("Frequenza settata a PERIODIC con successo.");	
		else System.out.println("Frequenza NON SETTATA CORRETTAMENTE.");		
			
		System.out.println("\n-------------------------------------------Cambiamento preferenza di attivit‡ nelgli UTENTI--------------------------------\n");	
		
		SetPreferencesController spc = new SetPreferencesController();
		if(spc.updatePreferencesUser(u, "tennis"))
			System.out.println("Cambiamento 1 avvenuto con SUCCESSO");	
		else System.out.println("Cambiamento 1 FALLITO");	

		User u2 = (User) dao.findSuperUser("drew@drew.com");
		if (spc.updatePreferencesUser(u2, "Calcio"))
			System.out.println("Aggiunto 'Calcio' con SUCCESSO all'utente "+u2.getUsername());	
		else System.out.println("Aggiunta di 'Calcio' FALLITA "+u2.getUsername());	
		
		if (spc.updatePreferencesUser(u2, "Boxe"))
			System.out.println("Aggiunto 'Boxe' con SUCCESSO all'utente "+u2.getUsername());	
		else System.out.println("Aggiunta di 'Boxe' FALLITA "+u2.getUsername());	
		
		System.out.println("\n-------------------------------------------Cambiamento preferenza di attivit‡ nelle 'ATTIVITA''--------------------------------\n");	
		if(spc.updatePreferencesActivity(modifiedsua, "CALCio") && spc.updatePreferencesActivity(modifiedsua, "tennIS"))
			System.out.println("Cambiamento nell'attivit‡ "+modifiedsua.getName()+" avvenuto con SUCCESSO");	
		else System.out.println(modifiedsua.getName()+" non Ë stata modificata.");	
		
		modifiedsua = cf.findActivityByID(p, 1);
		if(spc.updatePreferencesActivity(modifiedsua, "CALCio") && spc.updatePreferencesActivity(modifiedsua, "tennIS"))
			System.out.println("Cambiamento nell'attivit‡ "+modifiedsua.getName()+" avvenuto con SUCCESSO");	
		else System.out.println(modifiedsua.getName()+" non Ë stata modificata.");	
		
		modifiedsua = cf.findActivityByID(p, 3);
		if(spc.updatePreferencesActivity(modifiedsua, "Boxe") && spc.updatePreferencesActivity(modifiedsua, "tennIS"))
			System.out.println("Cambiamento nell'attivit‡ "+modifiedsua.getName()+" avvenuto con SUCCESSO");	
		else System.out.println(modifiedsua.getName()+" non Ë stata modificata.");	
		
		modifiedsua = cf.findActivityByID(p, 5);
		if(spc.updatePreferencesActivity(modifiedsua, "Boxe") && spc.updatePreferencesActivity(modifiedsua, "calcio"))
			System.out.println("Cambiamento nell'attivit‡ "+modifiedsua.getName()+" avvenuto con SUCCESSO");	
		else System.out.println(modifiedsua.getName()+" non Ë stata modificata.");
		
		System.out.println("\n-------------------------------------------Ricerca di attivit‡ IN BASE ALLA PREFERENZA--------------------------------\n");
		System.out.println("\n-------------------------------------------------Ricerca della preferenza TENNIS--------------------------------------\n");	
		ArrayList<SuperActivity> resultingActivities = cf.findActivityByPreference("tennis");
		if(resultingActivities==(null)) System.out.println("Non Ë stato trovato niente.\n");
		else System.out.println("Qualcosa Ë stato trovato. Con numero di elementi : "+resultingActivities.size());
		System.out.println("\n------------------------------------------------------Risultato ottenuto----------------------------------------------\n");
		SuperActivity result;
		int i,j;
		for(i=0;i<resultingActivities.size();i++) {
			result = resultingActivities.get(i);
			System.out.println("\nID: "+result.getId()+"\nNome attivit‡: "+result.getName()+"\nID creatore: "+result.getCreator().getUserID()+"\nFrequenza: "+result.getFrequency()+"\nID posto: "+result.getPlace()+"\nPreferenze del posto:");
			for(j=0;j<result.getPreferences().size();j++) {
				System.out.println("\n"+result.getPreferences().get(j));
			}
		}
		
			
			
	}

}
