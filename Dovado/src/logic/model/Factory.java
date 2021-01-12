package logic.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Factory {
	private static ActivityVector a = ActivityVector.getActivityVector();
	
	public static void createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime) {
		a.addActivity( new NormalActivity(nome,u, p,openingTime,closingTime));
	}
	
	public static void createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		a.addActivity( new NormalActivity(nome,u, p,openingTime,closingTime,startDate,endDate));
	}
	
	public static void createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence ) {
		a.addActivity( new NormalActivity(nome,u, p,openingTime,closingTime,startDate,endDate,cadence));
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime) {
		a.addActivity( new CertifiedActivity(nome,u, p,openingTime,closingTime));
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		a.addActivity( new CertifiedActivity(nome,u, p,openingTime,closingTime,startDate,endDate));
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence ) {
		a.addActivity( new CertifiedActivity(nome,u, p,openingTime,closingTime,startDate,endDate,cadence));
	}

}
