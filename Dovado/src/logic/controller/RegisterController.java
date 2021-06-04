package logic.controller;
import java.util.regex.Pattern;

import logic.model.DAOSuperUser;

import java.util.regex.Matcher;

public class RegisterController {
	//Decidere se i pattern mantenerli qui o sul bean
	private Pattern patternPsw;
	private Pattern patternEmail;
	private Pattern patternUname;
	private DAOSuperUser dao;
	
	public RegisterController() {
		patternEmail = Pattern.compile(".+@.+\\.[a-z]+");
		patternPsw = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");
		patternUname = Pattern.compile("\\S");
	}
	
	public boolean addUser(String email, String username, String psw) {
		
		dao = DAOSuperUser.getInstance();
	
		if (dao.findSuperUser(email) != null) {
			System.out.println("L'utente esiste");
			return false;
		}
		else {
			System.out.println("Non esiste l'utente");
			dao.addUserToJSON(email, username, 0, psw);
			return true;}	
	}

}
