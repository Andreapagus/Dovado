package logic.model;

public class CertifiedActivity extends SuperActivity implements Activity {

	public CertifiedActivity(String nome, SuperUser c, int tipo, Place p) {
		super(nome, c, tipo, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void PlayActivity(User u) {
		System.out.println("Yay hai fatto l'activity certificata e ti becchi la ricompensa");
		u.getReward(100);
	}

}
