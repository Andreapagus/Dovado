package logic.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CertifiedActivity extends SuperActivity implements Activity {

	public CertifiedActivity(String nome, SuperUser c, Place p, LocalTime openingTime, LocalTime closingTime) {
		super(nome, c, p,openingTime,closingTime);
		// TODO Auto-generated constructor stub
	}
	
	public CertifiedActivity(String nome, SuperUser c, Place p, LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate) {
		super(nome, c, p,openingTime,closingTime,startDate,endDate);
		// TODO Auto-generated constructor stub
	}
	
	public CertifiedActivity(String nome, SuperUser c, Place p, LocalTime openingTime, LocalTime closingTime, LocalDate startDate, LocalDate endDate,Cadence cadence) {
		super(nome, c, p,openingTime,closingTime,startDate,endDate, cadence);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void PlayActivity(User u) {
		System.out.println("Yay hai fatto l'activity certificata e ti becchi la ricompensa");
		u.getReward(100);
	}


}
