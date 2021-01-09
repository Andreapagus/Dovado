package logic.model;

public class SuperActivity {
	private String name;
	private SuperUser creator;
	private int type;
	private Place place;
	
	public SuperActivity(String nome, SuperUser c, int tipo, Place p) {
		name= nome;
		creator = c;
		type = tipo;
		place = p;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SuperUser getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
