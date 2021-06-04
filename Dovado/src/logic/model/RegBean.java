package logic.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.controller.RegisterController;

public class RegBean {
private String email;
private String password;
private String password2;
private String username;
private String error;
private Pattern patternPsw;
private Pattern patternEmail;
private Pattern patternUname;
private RegisterController regController;

public RegBean() {
	patternEmail = Pattern.compile(".+@.+\\.[a-z]+");
	patternPsw = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[,.!?&]).{8,20})");
	patternUname = Pattern.compile(" ");
	regController = new RegisterController();
	error = null;
}
public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
} 

public String getPassword2() {
	return password2;
}

public void setPassword2(String password2) {
	this.password2 = password2;
}

public String getError() {
    return error;
}

public void setError(String error) {
    this.error = error;
} 


public boolean validate() {
	System.out.println(username +": "+ password2 + "  " + password);	
	
	// controllo la mail se è scritta in maniera corretta
	Matcher matchEmail = patternEmail.matcher(email);
	if (!matchEmail.matches()) {
			error = "Mail sintatticamente sbagliata";
			System.out.println("Mail sintatticamente sbagliata");
			
			return false;}
	
	//check sulla password		
	if (!password.equals(password2)) {
			error = "Le password non coincidono";
			return false;
		}
	
	
	Matcher matchPsw = patternPsw.matcher(password);
	if (!matchPsw.matches()) {
		error = "La password deve contenere almeno 8 cratteri e deve contenere numeri, lettere e un carattere tra:{',','.','&','!','?'} ";
		System.out.println("password non conforme");
		return false;}
	
	//check sul nome utente e sulla presenza di spazi NON funzionaaaaaaa
	Matcher matchUsr = patternUname.matcher(username);
	if (username.length()>15 || username.length()<4 || username.lastIndexOf(" ") != -1) {
	
		error = "Lo username deve essere composto da una sola parola senza l'utilizzo di spazi e deve avere dai 4 ai 15 caratteri";
		System.out.println("Spazi nell'username: " + username + username.length()+ "  ci sono questi spazi:  " + username.lastIndexOf(" "));		
		return false;}
	
	//Chiamo il controller che controlla se eventualmente già esiste e lo aggiunge in alternativa
	if (!regController.addUser(email, username, password)) {
		error = "Esiste già un utente associato a questa email";
		return false;
	}
	
	return true;
	
	
}
}
