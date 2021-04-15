package logic.model;

public class Partner extends SuperUser{

	public String name;
	
	public Partner(String usr, String email,Long id) {
		super(usr, email,id);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	

}
