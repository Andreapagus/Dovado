package logic.model;
import java.util.ArrayList;


/**
 * 
 * @author sav
 * motivi per la creazione di activity vector
 * 
 * npratica ho pensato che TUTTE le activity vanno infilate in un qualche contenitore globale
 * il miglior modo per farlo è creare un singleton condiviso fra tutti gli utenti contenente le sfide
 * possibili migliorimanti da fare
 * renderlo thread safe
 * forse questo può essere sostituito da factory
 * 
 *
 */
public class ActivityVector {
	private ArrayList<Activity> globalActivity;
	private static ActivityVector istance = null;
	private static int noi;
	
	private ActivityVector() {
		globalActivity = new ArrayList<Activity>();
		noi = 0;
	}
	
	public static ActivityVector getActivityVector(){
		if(istance == null) istance = new ActivityVector();
		return istance;
	}
	
	public void addActivity(Activity a) {
		
		globalActivity.add(a);
		noi++;
	
	}
	
	public ArrayList<Activity> getActivity(){
		return globalActivity;
	}
	
	public int getNumberOfInstances() {
		return noi;
	}

}
