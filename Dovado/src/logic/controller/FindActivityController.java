package logic.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.model.Cadence;
import logic.model.CertifiedActivity;
import logic.model.DAOActivity;
import logic.model.DAOSuperUser;
import logic.model.NormalActivity;
import logic.model.Place;
import logic.model.SuperActivity;
import java.util.ArrayList;

public class FindActivityController {
	
	DAOSuperUser daoSU;
	DAOActivity daoAc;
	
	public ArrayList<SuperActivity> findActivityByPreference(String preference) {
		ArrayList<SuperActivity> result;
		daoAc = DAOActivity.getInstance();
		daoSU = DAOSuperUser.getInstance();
		if((result = daoAc.findActivityByPreference(daoSU, preference))!=null)
			return result;
		System.out.println("Non è stata trovata alcuna attività con la preferenza specificata.");
		return null;
	}
	//Il metodo prende in ingresso un posto e un intero che rappresenta l'id dell'attività che si vuole
	//trovare.
	public SuperActivity findActivityByID (Place p, int n) {
		daoSU = DAOSuperUser.getInstance();
		daoAc = DAOActivity.getInstance();
		return daoAc.findActivityByID(daoSU, p, n);
	}
	
	public boolean isInJSON(Place p, String activityName, Long creatorId) {
		daoSU = DAOSuperUser.getInstance();
		daoAc = DAOActivity.getInstance();
		return daoAc.isInJSON(daoSU, p, activityName, creatorId);
	}
}
