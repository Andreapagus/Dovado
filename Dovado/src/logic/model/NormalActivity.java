package logic.model;

public class NormalActivity extends SuperActivity implements Activity{

	public NormalActivity(String nome, SuperUser c, int tipo, Place p) {
		super(nome, c, tipo, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void PlayActivity(User u) {
		System.out.println("Yay hai fatto l'activity");
	}

}
