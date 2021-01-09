package logic.model;
import java.util.ArrayList;

public class Place {
	private int id;
	private String name;
	private Partner owner;
	private ArrayList<Activity> hostedActivities;
	
	public Place(int id,String name){
		this.id = id;
		this.name = name;
		owner = null;
		hostedActivities = new ArrayList<Activity>();
	}
	
	public Place(int id, String name,Partner o) {
		this(id,name);
		setOwner(o);
	}
	
	public void setOwner(Partner o) {
		owner = o;
	};
	
	public void addActivity(Activity a) {
		hostedActivities.add(a);
	}

}
