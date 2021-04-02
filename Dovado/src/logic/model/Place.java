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
	
	//Modificato l'arraylist di attività in un array di long, in questo modo
	//Verranno indicate le attività a cui fa riferimento il posto mediante il loro id.
	private ArrayList<Long> hostedActivities;
	
	public Place(String name,String address,String city,String region,String civico){
		this.name = name;
		this.address = address;
		this.city = city;
		this.region = region;
		this.civico = civico;
		this.owner = null;
		this.hostedActivities = new ArrayList<Long>();
		
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
	
	public void addActivity(Long id) {
		this.hostedActivities.add(id);
	}

	public int getId() {
		return this.id;
	};

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

	public Partner getOwner() {
		return this.owner;
	}

	public Object getCivico() {
		return this.civico;
	}
}
