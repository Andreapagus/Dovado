package logic.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import logic.model.Cadence;

public class CreateActivityBean {
	private int startYear;
	private int startMonth;
	private int startDay;
	private int startHour;
	private int startMinutes;
	
	private int endYear;
	private int endMonth;
	private int endDay;
	private int endHour;
	private int endMinutes;
	
	private ActivityType type;
	private Cadence cadence;
	
	
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public void setEndMinutes(int endMinutes) {
		this.endMinutes = endMinutes;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public void setCadence(Cadence cadence) {
		this.cadence = cadence;
	}

	public LocalTime getOpeningTime() {
		return LocalTime.of(startHour, startMinutes);
	}
	
	public LocalTime getClosingTime() {
		return LocalTime.of(endHour, endMinutes);
	}
	
	public LocalDate getStartDate() {
		return LocalDate.of(startYear, startMonth, startDay);
	}
	
	public LocalDate getEndDate() {
		return LocalDate.of(endYear, endMonth, endDay);
	}
	
	public Cadence getCadence() {
		return cadence;
	}
	
	public ActivityType getType() {
		return type;
	}
	
	
	
	
	
	
	
}
