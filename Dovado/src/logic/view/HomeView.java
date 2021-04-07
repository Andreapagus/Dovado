package logic.view;

import java.io.File;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HomeView implements Initializable{
	
    
    @FXML
    private WebView map;
    
    @FXML
    private ListView<String> userChallenges;
	
	public static void render(Stage current) {
    	try {
			VBox root = new VBox();
			BorderPane navbar = Navbar.getNavbar();
			Navbar.authenticatedSetup();
			Scene scene = new Scene(root,Navbar.getWidth(),Navbar.getHeight());
			scene.getStylesheets().add(Main.class.getResource("Dovado.css").toExternalForm());
			current.setTitle("Dovado - home");
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
		System.out.println("ok");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));		
		try{
			
			//map.getEngine().load("https://www.google.it/maps");
			//map.getEngine().load("http://bl.ocks.org/awoodruff/e9739a6719e0604eef58");
			
			
			File f = new File("WebContent/map.html");
			map.getEngine().setJavaScriptEnabled(true);
			//System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
			map.getEngine().load(f.toURI().toString());
			
			map.getEngine().executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}"); 
			
			System.out.println(map.getEngine().getUserAgent());
		}catch(Error e) {
			System.out.println(e);
		}
	}
}
