package logic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class SuperActivity {
	private String name;
	private SuperUser creator;
	private Place place;
	private FrequencyOfRepeat frequencyOfRepeat;
	
	public SuperActivity(String nome, SuperUser c, Place p) {
		//chiamare questo metodo quando si vuole creare una attività continua!
		name= nome;
		creator = c;
		place = p;
		frequencyOfRepeat = new ContinuosActivity(null,null);
	}
	
	public SuperActivity(String nome, SuperUser c, Place p, LocalTime openingTime, LocalTime closingTime) {
		//chiamare questo metodo quando si vuole creare una attività continua con orario apertura e chiusura
		this(nome,c,p);
		frequencyOfRepeat = new ContinuosActivity(openingTime,closingTime);
	}
	
	public SuperActivity(String nome, SuperUser c, Place p, LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		//chiamare questo metodo quando si vuole creare una attività a scadenza!
		this(nome,c,p);
		frequencyOfRepeat = new ExpiringActivity(openingTime,closingTime,startDate,endDate);
	}
	
	public SuperActivity(String nome, SuperUser c, Place p, LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence) {
		//chiamare questo metodo quando si vuole creare una attività periodica !
		this(nome,c,p);
		frequencyOfRepeat = new PeriodicActivity(openingTime,closingTime,startDate,endDate,cadence);
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SuperUser getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public boolean playableOnThisDate(LocalDateTime timestamp) {
		/*
		 * Metodo usato per capire se questa attività è fattibile in un dato giorno
		 * 
		 * 
		 */
		if (frequencyOfRepeat.checkPlayability(timestamp)) return true;
		else return false;
	}
}
