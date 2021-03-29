package logic.view;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class NavbarController {
	
	private Navbar nav= new Navbar();
	
    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem logout_input;
    
    @FXML
    void Logout(ActionEvent event) {
    	System.out.println("Clicked Logout");
    	nav.onHome();
    	Stage current = (Stage)((Node)Navbar.getNavbar()).getScene().getWindow();
    	Navbar.loginSetup();
    	LoginView.render(current);
    }

    @FXML
    void goChallenges(ActionEvent event) {
    	nav.onChallenges();
    	System.out.println("Clicked challenges");
    }

    @FXML
    void goHome(ActionEvent event) {
    	nav.onHome();
    	System.out.println("Clicked home");
    }

    @FXML
    void goProfile(ActionEvent event) {
    	nav.onMyProfile();
    	System.out.println("Clicked My profile");
    }

    @FXML
    void goVote(ActionEvent event) {
    	nav.onVote();
    	System.out.println("Clicked vote");
    }
    

}
