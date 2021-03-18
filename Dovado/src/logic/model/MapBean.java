package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logic.controller.ShowMapController;

public class MapBean {
	static double lat;
	static double lon;
	
	public static void getCoordinates() {
/*		ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
		try {
			engine.eval(new FileReader("index.html"));
			Invocable invocable = (Invocable)engine;
			lat = invocable.InvokeFunction("getLatitude");
		} catch(ScriptException | FileNotFoundException e) {
			e.printStackTrace();
		}
*/	}
	
	public JSONArray getPlaces() {
		JSONArray places = ShowMapController.getInstance().getPlaces();
		return places;
	}
	
	/*
	public static double getLatitude() {		
	}
	
	public static double getLongitude() {		
	}
	*/
}
