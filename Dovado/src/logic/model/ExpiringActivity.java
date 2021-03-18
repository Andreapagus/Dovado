package logic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ExpiringActivity extends FrequencyOfRepeat {
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	public ExpiringActivity(LocalTime openingTime, LocalTime closingTime,LocalDate startDate, LocalDate endDate) {
		super(openingTime, closingTime);
		
		this.startDate=startDate;
		this.endDate=endDate;
		
	}

	
	@Override
	public boolean checkPlayability(LocalDateTime timestamp) {
		if(!this.isOnTime(timestamp)) return false;
		
		
		LocalDate myDate = timestamp.toLocalDate();
		
		if((startDate.isAfter(myDate) && endDate.isBefore(myDate)) || (startDate.isEqual(myDate) && endDate.isEqual(myDate)) )
		return true;
		
		else return false;
	} 

	public LocalDate getStartDate() {
		return this.startDate;
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}
	
	
}
