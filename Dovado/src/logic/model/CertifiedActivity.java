package logic.model;

public class CertifiedActivity extends KindOfActivity {
	private Partner owner;
	
	
	public CertifiedActivity(Partner p){
		this.owner= p;
	}
	
	@Override
	public void playActivity(User u) {
		System.out.println("Yay hai fatto l'activity certificata e ti becchi la ricompensa");
		u.getReward(100);
	}


}
