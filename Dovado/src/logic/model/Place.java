package logic.model;
import java.util.ArrayList;

public class Place {
	private int id;
	private String civico;
	private String city;
	private String region;
	private String address;
	private String name;
	private Partner owner;
	private ArrayList<Activity> hostedActivities;
	
	public Place(String name,String address,String city,String region,String civico){
		this.name = name;
		this.address = address;
		this.city = city;
		this.region = region;
		this.civico = civico;
		owner = null;
		hostedActivities = new ArrayList<Activity>();
		
	}
	
	public Place(String name,String address,String city,String region,String civico,Partner o) {
		this(name,address,city,region,civico);
		setOwner(o);
	}

	public void setOwner(Partner o) {
		this.owner = o;
	};
	
	public void setId(int id) {
		this.id = id;
	};
	
	public void addActivity(Activity a) {
		this.hostedActivities.add(a);
	}

	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}

	public String getCity() {
		return this.city;
	}
	
	public String getRegion() {
		return this.region;
	}
}
