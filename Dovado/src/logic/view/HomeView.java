package logic.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import com.sun.javafx.fxml.builder.web.WebEngineBuilder;
import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.model.DAOActivity;
import logic.model.DAOPlace;
import logic.model.DAOPreferences;
import logic.model.DAOSuperUser;
import logic.model.SuperActivity;

public class HomeView implements Initializable{

	public static boolean homeLoaded = false;
    @FXML
    private Button preference1;

    @FXML
    private Button preference2;
    
    @FXML
    private Button preference3;

    @FXML
    private ListView<Object> eventsList;
    
    @FXML
    private WebView map;

    @FXML
    private WebEngine eng;
    

    private static DAOPreferences daoPref;
    private static DAOActivity daoAct;
    private static DAOSuperUser daoSU;
    private static DAOPlace daoPlc;
    private static SuperActivity activitySelected;
    
	public static void render(Stage current) {
    	int i;
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
		daoPref = DAOPreferences.getInstance();
		daoAct = DAOActivity.getInstance();
		daoSU = DAOSuperUser.getInstance();

    	ArrayList<SuperActivity> activities = new ArrayList<SuperActivity>();
		
    	System.out.println("ok");
		System.out.println("Working Directory = " + System.getProperty("user.dir"));		
		try{
			eng = map.getEngine();
			eng.load("file:///C:/Users/Andrew/Desktop/NuovaRoba/Dovado/WebContent/map.html");
			
			// Setting permissions to interact with Js
	        eng.setJavaScriptEnabled(true);
	        
			
	        preference1.setText(daoPref.getPreferenceFromJSON(1));
			preference2.setText(daoPref.getPreferenceFromJSON(2));
			preference3.setText(daoPref.getPreferenceFromJSON(3));
			
			activities.addAll(daoAct.findActivityByPreference(daoSU, "BOXE"));
			activities.addAll(daoAct.findActivityByPreference(daoSU, "TENNIS"));

			System.out.println("\nNumero di attività trovate: "+activities.size());

			int j;
			for(j=0;j<activities.size();j++)
				System.out.println("tutte le attività "+activities.get(j).getId());
			
			Thread newThread = new Thread(() -> {
				int i;
				for(i=0;i<activities.size();i++) {
					ImageView eventImage = new ImageView();
					Text eventName = new Text(activities.get(i).getName()+"\n");
					System.out.println("\n\n"+activities.get(i).getName()+"\n\n");
					Text eventInfo = new Text(activities.get(i).getPlace().getName()+
							"\nFrom "+activities.get(i).getFrequency().getOpeningTime()+
							" to "+activities.get(i).getFrequency().getClosingTime());
					eventImage.setImage(new Image("https://source.unsplash.com/user/erondu/200x100"));

					eventInfo.setFont(Font.font("Monserrat-Black", FontWeight.BLACK, 12));

					eventName.setFont(Font.font("Monserrat-Black", FontWeight.BLACK, 20));
					eventName.setFill(Paint.valueOf("#ffffff"));
					eventName.setStroke(Paint.valueOf("#000000"));

					eventInfo.setFill(Paint.valueOf("#ffffff"));

					eventInfo.setStroke(Paint.valueOf("#000000"));
					
					
					VBox eventText = new VBox(eventName,eventInfo);
					eventText.setAlignment(Pos.CENTER);
					
					StackPane eventBox = new StackPane();
					Text eventId = new Text();
					Text placeId = new Text();

					eventId.setId(activities.get(i).getId().toString());
					placeId.setId(activities.get(i).getPlace().getId().toString());
					
					eventBox.getChildren().add(eventId);
					eventBox.getChildren().add(placeId);
					eventBox.getChildren().add(eventImage);
					eventBox.getChildren().add(eventText);
					
					eventBox.setAlignment(Pos.CENTER);
					
					eventsList.getItems().add(eventBox);
				}
			});
			newThread.start();
			eventsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			newThread.join();
		}catch(Error e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void activitySelected() {
		daoAct = DAOActivity.getInstance();
		daoSU = DAOSuperUser.getInstance();
		daoPlc = DAOPlace.getInstance();
		
		StackPane eventBox = (StackPane) eventsList.getSelectionModel().getSelectedItem();
		
		int activityId = Integer.parseInt(eventBox.getChildren().get(0).getId());
		Long placeId = Long.parseLong(eventBox.getChildren().get(1).getId());
		activitySelected = daoAct.findActivityByID(daoSU,daoPlc.findPlaceById(placeId),activityId); 
		System.out.println("Attività trovata: "+activitySelected);
		
		eng.executeScript("spotPlace('"+activitySelected.getPlace().getCivico()+"','"+activitySelected.getPlace().getAddress()+"','"+activitySelected.getPlace().getCity()+"','"+activitySelected.getPlace().getRegion()+"')");
		
	}
	
	private void loadSplashScreen() {
		
	}
}
