package logic.model;

import java.util.ArrayList;

public abstract class SuperUser {
	private String username;
	private Long uID;
	private String email;
	private ArrayList<String> preferences;
	
	public SuperUser(String usr,String email,Long id) {
		this.username = usr;
		this.email = email;
		this.uID = id;
		this.preferences = new ArrayList<String>();
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Long getUserID() {
		return this.uID;
	}

	/*Si aggiungono due nuovi metodi: getPreferences, che restituisce
	* un ArrayList<String> contenente tutte le preferenze dell'istanza
	* di SuperUser, e setPreferences, che invece stabilisce le nuove 
	* preferenze del SuperUser in questione.
	*/
	public ArrayList<String> getPreferences() {
		return this.preferences;
	}
	
	public void setPreferences(ArrayList<String> newPreferences) {
		this.preferences = newPreferences;
	}
	
	public SuperUser getUser(String username) {
		if(this.username.equals(username))
			return this;
		return null;
	}
	
}
