package logic.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Button register_input;

    @FXML
    private Button login_input;

    @FXML
    private Label label_register;

    @FXML
    void login(ActionEvent event) {
    	Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();
    	LoginView.render(current);
    }

    @FXML
    void register(ActionEvent event) {
    	System.out.println("User registered");
    }
    
    public static void render(Stage current) {
		Stage primaryStage = current;
		try {
			VBox root = new VBox();
			BorderPane navbar = Navbar.getNavbar();
			Scene scene = new Scene(root,Navbar.getWidth(),Navbar.getHeight());
			scene.getStylesheets().add(Main.class.getResource("Dovado.css").toExternalForm());
			primaryStage.setTitle("GoodHabits - Register");
			primaryStage.setScene(scene);
			GridPane home = new GridPane();
			try {
				home = FXMLLoader.load(Main.class.getResource("Register.fxml"));
			} catch(IOException e) {
				e.printStackTrace();
			}
			root.getChildren().addAll(navbar,home);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
