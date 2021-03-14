package logic.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Factory {
	private static ActivityVector a = ActivityVector.getActivityVector();
	
	public static void createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime) {
		SuperActivity activity = new SuperActivity(nome,u, p,openingTime,closingTime);
		
		a.addActivity(activity);
	}
	
	public static void createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		SuperActivity activity = new SuperActivity(nome,u, p,openingTime,closingTime,startDate,endDate);
		
		a.addActivity( activity);
	}
	
	public static void createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence ) {
		SuperActivity activity = new SuperActivity(nome,u, p,openingTime,closingTime,startDate,endDate,cadence);
		
		a.addActivity( activity);
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime) {
		SuperActivity activity = new SuperActivity(nome,u, p,openingTime,closingTime);
		
		
		activity.reclaimActivity((Partner)u);
		
		a.addActivity( activity);
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		SuperActivity activity = new SuperActivity(nome,u, p,openingTime,closingTime,startDate,endDate);
		activity.reclaimActivity((Partner)u);
		
		a.addActivity( activity);
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence ) {
		SuperActivity activity = new SuperActivity(nome,u, p,openingTime,closingTime,startDate,endDate,cadence);
		activity.reclaimActivity((Partner)u);
		
		a.addActivity( activity);
	}

}
