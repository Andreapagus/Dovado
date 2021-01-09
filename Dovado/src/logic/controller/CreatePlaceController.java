package logic.controller;

import logic.model.Partner;
import logic.model.Place;
import logic.model.PlaceVector;
import logic.model.SuperUser;

public class CreatePlaceController {
	private SuperUser usr;
	
	public CreatePlaceController(SuperUser usr) {
		this.usr = usr;
	}
	
	public Place CreatePlace(String name, Partner owner){
		PlaceVector.getPlaceVector();
		return PlaceVector.addPlace(name, owner);
	}

}
