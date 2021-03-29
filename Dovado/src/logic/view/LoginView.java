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

public class LoginView{
	
    @FXML
    private Button login_input;

    @FXML
    private Button register_input;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label fail;

    @FXML
    private Label label_register;

    @FXML
    void login(ActionEvent event) {
    	System.out.println("Clicked login");
    	Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();
    	HomeView.render(current);
    }

    @FXML
    void register(ActionEvent event) {
    	System.out.println("Clicked register");
    	Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();
    	RegisterView.render(current);
    }
    
    public static void render(Stage current) {
		Stage primaryStage = current;
		try {
			VBox root = new VBox();
			BorderPane navbar = Navbar.getNavbar();
			Scene scene = new Scene(root,Navbar.getWidth(),Navbar.getHeight());
			scene.getStylesheets().add(Main.class.getResource("Dovado.css").toExternalForm());
			primaryStage.setTitle("GoodHabits - login");
			primaryStage.setScene(scene);
			GridPane login = new GridPane();
			try {
				login = FXMLLoader.load(Main.class.getResource("Login.fxml"));
			} catch(IOException e) {
				e.printStackTrace();
			}
			root.getChildren().addAll(navbar,login);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}