package logic.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class FrequencyOfRepeat {
	private LocalTime openingTime;
	private LocalTime closingTime;
	
	public FrequencyOfRepeat(LocalTime openingTime, LocalTime closingTime) {
		this.openingTime=openingTime;
		this.closingTime=closingTime;
	}
	
	
	public boolean isOnTime(LocalDateTime myLocalTime) {
		/*compareTo è un metodo di LocalTime che restituisce:
			int > 0 se LocalTime > myTime
			int = 0 se LocalTime = myTime
			int < 0 se LocalTime < mytime
		*/
		
		LocalTime myTime = myLocalTime.toLocalTime();
		
		if(openingTime == null || closingTime == null) return true;
		
		
		int t1 = openingTime.compareTo(myTime); //this should be <= 0 (orario di apertura minore o uguale al tempo desiderato)
		int t2 = closingTime.compareTo(myTime); // this sould be >0 (orario di chiusura più grande di tempo desiderato )
		
		if(t1<= 0 && t2 >0) return true;
		else return false;
	}
	
	
	public abstract boolean checkPlayability(LocalDateTime timestamp);

}
