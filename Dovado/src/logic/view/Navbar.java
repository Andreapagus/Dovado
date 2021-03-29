package logic.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/*
 * simple singleton implementation of the navbar due to:
 * 1- i need to use navbar from almost every page and there is no point on instance a different navbar for every page
 * 2- i didn't used The multithread safe navbar since i can't use more than one active program on the same computer
 */
public class Navbar {
	private static BorderPane navbar = null;
	
	public static BorderPane getNavbar() {
		if(Navbar.navbar == null) 
			try {
				Navbar.navbar = FXMLLoader.load(Main.class.getResource("navbar.fxml"));
				Navbar.loginSetup();
			} catch(IOException e) {
				e.printStackTrace();
			}
		return navbar;
	}
	
	public static double getHeight() {
		try {
		return Navbar.getNavbar().getScene().getHeight();
		} catch(Exception e) {
			return 480;
		}
		
	}
	
	public static double getWidth() {
		try{
		return Navbar.getNavbar().getScene().getWidth();
		} catch(Exception e) {
			return 640;
		}
	}
	
	public static void loginSetup() {
	  	for(Node curr : navbar.lookupAll(".navbar-item")) {
	  		curr.setDisable(true);
	    	curr.setVisible(false);
	    }
	}
	    
	public static void authenticatedSetup() {
		for(Node curr : navbar.lookupAll(".navbar-item")) {
	       	curr.setDisable(false);
	       	curr.setVisible(true);
	    }
	}
	
	public void onHome() {
		this.changePage();
		navbar.lookup("#home").getStyleClass().add("active");
	}
	
	public void onChallenges() {
		this.changePage();
		navbar.lookup("#challenges").getStyleClass().add("active");
	}
	
	public void onVote() {
		this.changePage();
		navbar.lookup("#vote").getStyleClass().add("active");
	}
	
	public void onMyProfile() {
		this.changePage();
		navbar.lookup("#myProfile").getStyleClass().add("active");
	}
	
	private void changePage() {
		for(Node curr : navbar.lookupAll(".navbar-item")) {
	  		curr.getStyleClass().remove("active");
	    }
	}
	
	
}
