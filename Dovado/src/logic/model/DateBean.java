package logic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * qua si mette il controllo del formato di data corretta per evitare cazzate
 * TODO
 * 
 *  */

public class DateBean {
	private int yearS;
	private int monthS;
	private int dayS;
	private int hourS;
	private int minutesS;
	
	private int yearR;
	private int monthR;
	private int dayR;
	private int hourR;
	private int minutesR;
	
	public int getYear() {
		return yearS;
	}
	public void setYear(int year) {
		this.yearS = year;
	}
	public int getMonth() {
		return monthS;
	}
	public void setMonth(int month) {
		this.monthS = month;
	}
	public int getDay() {
		return dayS;
	}
	public void setDay(int day) {
		this.dayS = day;
	}
	public int getHour() {
		return hourS;
	}
	public void setHour(int hour) {
		this.hourS = hour;
	}
	public int getMinutes() {
		return minutesS;
	}
	public void setMinutes(int minutes) {
		this.minutesS = minutes;
	}
	
	public LocalDateTime getScheduledTime() {
		
		LocalDateTime scheduledTime = LocalDateTime.of(
				LocalDate.of(yearS,monthS,dayS), 
				LocalTime.of(hourS, minutesS)
				);	
		
		return scheduledTime;
	}
	
	public LocalDateTime getReminderTime() {
		LocalDateTime reminderTime;
		try {
			reminderTime = LocalDateTime.of(
				LocalDate.of(yearR,monthR,dayR), 
				LocalTime.of(hourR, minutesR)
			);
		} catch (Exception e) {
			reminderTime = null;
		}
		
		return reminderTime;
	}
	

}
