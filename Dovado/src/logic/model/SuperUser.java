package logic.model;

public abstract class SuperUser {
	private String username;
	
	public SuperUser(String usr) {
		this.username = usr;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public SuperUser getUser(String username) {
		if(this.username.equals(username))
			return this;
		return null;
	}
	
}
