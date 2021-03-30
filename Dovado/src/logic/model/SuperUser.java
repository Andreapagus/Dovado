package logic.model;

public abstract class SuperUser {
	private String username;
	private int uID;
	
	public SuperUser(String usr) {
		this.username = usr;
		this.uID = 1;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public int getUserID() {
		return this.uID;
	}
	
	public SuperUser getUser(String username) {
		if(this.username.equals(username))
			return this;
		return null;
	}
	
}
