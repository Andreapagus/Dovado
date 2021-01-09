package logic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule {
	private ArrayList<ScheduledActivity> myActivity;
	
	public Schedule() {
		myActivity = new ArrayList<ScheduledActivity>();
	}
	
	public void addActivityToSchedule(Activity a, LocalDateTime scheduledTime, LocalDateTime reminderTime) {
		ScheduledActivity s = new ScheduledActivity(a,scheduledTime,reminderTime);
		myActivity.add(s);
	}
	

}
