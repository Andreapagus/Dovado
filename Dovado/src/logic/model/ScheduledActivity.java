package logic.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* 
 * dal momento che java ha deprecato praticamente tutto sulle date va incapsulato così
 *
 * Reference for date: https://www.html.it/pag/61171/gestione-date-in-java/
 * 
 * madonna se odio la mia vita
 * 
 * Gestire i reminder:
 * 
 * è possibile utilizzare la classe timer di Java
 * reference:
 * 	https://stackoverflow.com/questions/11361332/how-to-call-a-method-on-specific-time-in-java
 * 
 * 
 * 
 *  
 * */

public class ScheduledActivity {
	
	private Activity referencedActivity;
	private LocalDateTime scheduledTime;
	private LocalDateTime reminderTime;
	private Timer timer;
	
	//prob qua useremo un javabean per contenere tutte ste stronzate
	public ScheduledActivity(Activity a, LocalDateTime scheduledTime, LocalDateTime reminderTime) {
		
		//se reminderTime è nulla allora il reminder viene messo come ora di schedulo!
		
		this.referencedActivity = a;
		this.scheduledTime = scheduledTime;
		if(reminderTime != null) this.reminderTime = reminderTime;
		else this.reminderTime = scheduledTime;
		
		this.scheduleReminder();
	}
	
	
	//reference : https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	private Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}
	
	private void scheduleReminder() {
		timer = new Timer();
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("devi fare il task");
			}
		};
		
		timer.schedule( task , convertToDateViaSqlTimestamp(reminderTime));
	}
	

}
