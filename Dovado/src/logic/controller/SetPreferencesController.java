package logic.controller;

import java.util.ArrayList;

import logic.model.DAOActivity;
import logic.model.DAOPreferences;
import logic.model.DAOSuperUser;
import logic.model.SuperActivity;
import logic.model.SuperUser;

public class SetPreferencesController {
	
	DAOSuperUser daoSu;
	DAOActivity daoAc;
	DAOPreferences daoPr;
	
	public boolean updatePreferencesUser(SuperUser su, String name) {
		Long id;
		daoSu = DAOSuperUser.getInstance();
		daoPr = DAOPreferences.getInstance();
		//Prendo l'arraylist delle preferenze e lo preparo.
		ArrayList<Long> preferences = su.getPreferences();
		
		//Tramite il DAO delle preferenze vado a cercare il suo id usando il nome.
		//Se il nome non esiste il metodo chiamato restituisce l'id dell'appena aggiunta
		//preferenza.
		id = daoPr.findPreferenceId(name);
		if(preferences.contains(id))
			return true;
		preferences.add(id);
		su.setPreferences(preferences);
		
		if(daoSu.updateUserPreferences(su))
			return true;
		return false;
	}
	
	public boolean updatePreferencesActivity(SuperActivity sua, String name) {
		Long id;
		daoAc = DAOActivity.getInstance();
		daoPr = DAOPreferences.getInstance();
		//Prendo l'arraylist delle preferenze e lo preparo.
		ArrayList<Long> preferences = sua.getPreferences();
		
		//Tramite il DAO delle preferenze vado a cercare il suo id usando il nome.
		//Se il nome non esiste il metodo chiamato restituisce l'id dell'appena aggiunta
		//preferenza.
		id = daoPr.findPreferenceId(name);
		if(preferences.contains(id))
			return true;
		preferences.add(id);
		sua.setPreferences(preferences);
		
		if(daoAc.updateActivityPreferences(sua))
			return true;
		return false;
	}
	
	
}
