package logic.model;

public abstract class SuperUser {
	private String username;
	private Long uID;
	
	public SuperUser(String usr,Long id) {
		this.username = usr;
		this.uID = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Long getUserID() {
		return this.uID;
	}
	
	public SuperUser getUser(String username) {
		if(this.username.equals(username))
			return this;
		return null;
	}
	
}
