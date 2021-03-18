package logic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import logic.controller.CreateActivityBean;

public class Factory {
	private static SuperActivity newActivity;
	
	public static SuperActivity createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime) {
		newActivity = new NormalActivity(nome,u, p,openingTime,closingTime);
		return newActivity;
	}
	
	public static SuperActivity createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		newActivity = new NormalActivity(nome,u, p,openingTime,closingTime,startDate,endDate);
		return newActivity;
	}
	
	public static SuperActivity createNormalActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence ) {
		newActivity = new NormalActivity(nome,u, p,openingTime,closingTime,startDate,endDate,cadence);
		return newActivity;
	}
	
	public static SuperActivity createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime) {
		newActivity = new CertifiedActivity(nome,u, p,openingTime,closingTime);
		return newActivity;
	}
	
	public static SuperActivity createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		newActivity = new CertifiedActivity(nome,u, p,openingTime,closingTime,startDate,endDate);
		return newActivity;
	}
	
	public static SuperActivity createCertifiedActivity(String nome, SuperUser u, Place p,LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence ) {
		newActivity = new CertifiedActivity(nome,u, p,openingTime,closingTime,startDate,endDate,cadence);
		return newActivity;
	}

}
