package logic.controller;

import logic.model.DAOPlace;
import logic.model.Partner;
import logic.model.Place;

public class ClaimAPlaceController {
	
	private DAOPlace daoPl;
	
	public boolean claimPlaceOwnership(Partner owner, Place p) {
		daoPl=DAOPlace.getInstance();
		if(p.getOwner()==(null)) {
			p.setOwner(owner);
			if(daoPl.updatePlaceJSON(p)==true)
				return true;
			else
			{
				System.out.println("Errore incontrato nel processo di aggiunta proprietario.\n");
				return false;
			}
		}
		return false;
	}
	
}
