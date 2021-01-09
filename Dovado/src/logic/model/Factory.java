package logic.model;

public class Factory {
	private static ActivityVector a = ActivityVector.getActivityVector();
	
	public static void createNormalActivity(String nome, SuperUser u, int type, Place p) {
		a.addActivity( new NormalActivity(nome,u,type, p));
	}
	
	public static void createCertifiedActivity(String nome, SuperUser u, int type, Place p) {
		a.addActivity(new CertifiedActivity(nome,u,type, p));
	}
}
