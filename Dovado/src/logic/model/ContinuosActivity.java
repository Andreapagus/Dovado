package logic.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ContinuosActivity extends FrequencyOfRepeat {

	public ContinuosActivity(LocalTime openingTime, LocalTime closingTime) {
		super(openingTime, closingTime);
	}

	@Override
	public boolean checkPlayability(LocalDateTime timestamp) {
		if(this.isOnTime(timestamp))return true;
		return false;
	}

}
