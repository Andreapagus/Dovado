package logic.controller;

import logic.model.DAOActivity;
import logic.model.Partner;
import logic.model.SuperActivity;
import logic.model.User;

public class ClaimActivityController {
	
	private DAOActivity daoAc;
	
	public boolean claimActivityOwnership(Partner owner, SuperActivity a) {
		daoAc=DAOActivity.getInstance();
		if(a.getCreator() instanceof User) {
			a.setCreator(owner);
			if(daoAc.updateActivityJSON(a)==true)
				return true;
			else
			{
				System.out.println("Errore incontrato nel processo di aggiunta proprietario.\n");
				return false;
			}
		} else {
			System.out.println("Esiste già un partner proprietario dell'attività.\n");
			return false;	
		}
	}
	
}
