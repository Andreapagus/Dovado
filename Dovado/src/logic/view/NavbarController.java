package logic.view;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class NavbarController {
	
	private Navbar nav= new Navbar();

    @FXML
    private MenuButton menuButton;
    
    @FXML
    private ImageView imageView;

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
    void goChannels(ActionEvent event) {
    	nav.onChannels();
    	System.out.println("Clicked channels");
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
    void goPreferences(ActionEvent event) {
    	nav.onPreferences();
    	System.out.println("Clicked preferences");
    }

    @FXML
    void goEvents(ActionEvent event) {
    	nav.onEvents();
    	System.out.println("Clicked vote");
    }
    

}
