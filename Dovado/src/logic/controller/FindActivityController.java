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
	
	//Il metodo prende in ingresso un posto e un intero che rappresenta l'id dell'attività che si vuole
	//trovare.
	public SuperActivity findActivityByID (Place p, int n) {
		daoAc = DAOActivity.getInstance();
		return daoAc.findActivityByID(daoSU, p, n);
	}
	
	public boolean isInJSON(Place p, String activityName, Long creatorId) {
		daoAc = DAOActivity.getInstance();
		return daoAc.isInJSON(daoSU, p, activityName, creatorId);
	}
}
