package logic.model;
import java.util.ArrayList;

/*
 * Classe per creata per risolvere il problema di "come rendo tutti i posti
 * accessibili a tutti gli utenti?
 * 
 * implementazione di singleton di un array di posti
 * 
 * sarei più contento se il costruttore di place fosse accessibile solo da qui
 * 
 * 
 * */

public class PlaceVector {
	
	private ArrayList<Place> globalPlaces;
	private static PlaceVector istance = null;
	private static int noi;
	
	private PlaceVector() {
		globalPlaces = new ArrayList<Place>();
		noi = 0;
	}
	
	public static PlaceVector getPlaceVector(){
		if(istance == null) istance = new PlaceVector();
		return istance;
	}
	
	public static Place addPlace(String name, Partner owner) {
		noi++;
		Place p = new Place(noi,name,owner);
		PlaceVector.getPlaceVector().globalPlaces.add(p);
		return p;
	}
	
	public static ArrayList<Place> getPlaces() {
		return getPlaceVector().globalPlaces;
	}

}
