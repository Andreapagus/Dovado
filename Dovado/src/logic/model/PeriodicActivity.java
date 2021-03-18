package logic.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PeriodicActivity extends FrequencyOfRepeat{


	private LocalDate startDate;
	private LocalDate endDate;
	private Cadence cadence;
	
	public PeriodicActivity(LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate, Cadence cadence) {
		super(openingTime, closingTime);
		
		this.startDate=startDate;
		this.endDate=endDate;
		
		this.cadence = cadence;
	}

	public Cadence getCadence() {
		return this.cadence;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}
	
	
	@Override
	public boolean checkPlayability(LocalDateTime timestamp) {
		
		if(!this.isOnTime(timestamp)) return false;
		
		switch(cadence) {
		case WEEKLY:
			{	
				// reference for DayOfWeek = https://docs.oracle.com/javase/8/docs/api/java/time/DayOfWeek.html#getValue--
				DayOfWeek myDay = timestamp.getDayOfWeek();
				
				DayOfWeek startingDay = startDate.getDayOfWeek();
				DayOfWeek endDay = endDate.getDayOfWeek();
				
				
				
				if( myDay.getValue() >= startingDay.getValue() && myDay.getValue() <= endDay.getValue()) return true;
				
				else return false;
				
			}
		case MONTHLY:
			{	
				//getDayOfMonth restutisce un int che va da 1 a 31 in base al giorno!
				int myDate = timestamp.getDayOfMonth();
				
				int startDay = startDate.getDayOfMonth();
				int endDay = endDate.getDayOfMonth();
				
				if(myDate >= startDay && myDate <= endDay) return true;
				else return false;
				
			}
		case ANNUALLY: 
			{	
				
				int myMonth = timestamp.getMonthValue();
				
				int startMonth = startDate.getMonthValue();
				int endMonth = endDate.getMonthValue();
				
				if(!(myMonth >= startMonth && myMonth <= endMonth)) return false;
				
				
				//serious spaghetti code here:
				
				int myDate = timestamp.getDayOfMonth();
				
				int startDay = startDate.getDayOfMonth();
				int endDay = endDate.getDayOfMonth();
				
				if(myDate >= startDay && myDate <= endDay) return true;
				else return false;
				
			}
		}
		return true;
	}
}
