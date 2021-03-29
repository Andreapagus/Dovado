package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeView implements Initializable{
	
    @FXML
    private Text timer;

    @FXML
    private Text completed;

    @FXML
    private Text completedPerc;

    @FXML
    private Text failed;

    @FXML
    private Text failedPerc;

    @FXML
    private Text remaining;

    @FXML
    private Text remainingPerc;
    
    @FXML
    private ListView<String> userChallenges;
	
	public static void render(Stage current) {
    	try {
			VBox root = new VBox();
			BorderPane navbar = Navbar.getNavbar();
			Navbar.authenticatedSetup();
			Scene scene = new Scene(root,Navbar.getWidth(),Navbar.getHeight());
			scene.getStylesheets().add(Main.class.getResource("Dovado.css").toExternalForm());
			current.setTitle("GoodHabits - home");
			current.setScene(scene);
			VBox home = new VBox();
			try {
				home = FXMLLoader.load(Main.class.getResource("home.fxml"));
				VBox.setVgrow(home, Priority.SOMETIMES);
			} catch(IOException e) {
				e.printStackTrace();
			}
			root.getChildren().addAll(navbar,home);
			current.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> names = FXCollections.observableArrayList(
		          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
		userChallenges.setItems(names);
	}
}
