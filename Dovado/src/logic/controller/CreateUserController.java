package logic.controller;

import logic.model.DAOSuperUser;
import logic.model.Partner;
import logic.model.User;

public class CreateUserController {
	
	DAOSuperUser daoSU;
	// TODO in virtù i quello scritto sotto gli id sono statici
	// NON SO SE E' UTILE CREARE UN INTERO CONTROLLER PER L'AGGIUNTA DI UTENTI, MAGARI IL DAO VIENE CHIAMATO DALLA ENTITY? NON SEMBRA CORRETTO, MA OVVIAMENTE E' MENO CODICE.
	// Per il momento non vedo motivo per cuifar ritornare la classe partner/user quindi ho 
	public  int createPartner(String email, String username,String psw){
		daoSU = DAOSuperUser.getInstance();
		daoSU.addUserToJSON(email, username, 1,psw);
		Long tmpID = Long.valueOf(1);
		//return new Partner(username, tmpID);
		return 1;
	}
	
	public int createUser(String email, String username, String psw) {
		daoSU = DAOSuperUser.getInstance();
		daoSU.addUserToJSON(email, username, 0,psw);
		Long tmpID = Long.valueOf(1);
		//return new User(username, tmpID);
		return 1;
	}
	public void printTest(String n) {
		System.out.println(n);
	}
	
}
