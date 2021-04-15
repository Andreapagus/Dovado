package logic.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import logic.model.Cadence;
import logic.model.ContinuosActivity;
import logic.model.DAOActivity;
import logic.model.ExpiringActivity;
import logic.model.PeriodicActivity;
import logic.model.SuperActivity;

public class ManageActivityController {
	
	DAOActivity daoAc;
	
	/* Nel "Managing" di un'attività potremmo volerne cambiare il nome, 
	 * pertanto si aggiunge un apposito metodo in cui si controlla
	 * se il nome nuovo è diverso da quello attualmente settato nell'attività
	 * da noi passata.
	 */
	public SuperActivity setActivityName(SuperActivity sua,String name) {
		daoAc = DAOActivity.getInstance();
		if(!sua.getName().equals(name)) {
			sua.setName(name);
			daoAc.updateActivityJSON(sua);
		}
		return sua;
	}
	
	/* Nel "Managing" di un'attività potremmo anche volerla cancellare.
	 * chiameremo il metodo di DAOActivity deleteActivityJSON cancellandola
	 * così dalla persistenza.
	 */
	public boolean deleteActivity(SuperActivity sua) {
		daoAc = DAOActivity.getInstance();
		if(!daoAc.deleteActivityJSON(sua))
			return false;
		return true;
	}

	/* Nel "Managing" di un'attività potremmo anche voler cambiare la frequenza
	 * con cui l'attività va a ripetersi. Si controlla prima che l'attività non sia
	 * già del tipo della nuova frequenza, fatto ciò si aggiorna l'attività anche
	 * nella persistenza.
	 */
	public SuperActivity setFrequency(SuperActivity sua, LocalTime opening, LocalTime closing ) {
		daoAc = DAOActivity.getInstance();
		if (!(sua.getFrequency() instanceof ContinuosActivity)) {
			sua.setFrequency(new ContinuosActivity(opening,closing));
			daoAc.updateActivityJSON(sua);
		}
		return sua;
	}
	
	public SuperActivity setFrequency(SuperActivity sua, LocalTime opening, LocalTime closing, LocalDate start, LocalDate end) {
		daoAc = DAOActivity.getInstance();
		if (!(sua.getFrequency() instanceof ExpiringActivity)) {
			sua.setFrequency(new ExpiringActivity(opening,closing,start,end));
			daoAc.updateActivityJSON(sua);
		}
		return sua;
	}
	
	public SuperActivity setFrequency(SuperActivity sua, LocalTime opening, LocalTime closing, LocalDate start, LocalDate end, Cadence cadence) {
		daoAc = DAOActivity.getInstance();
		if (!(sua.getFrequency() instanceof PeriodicActivity)) {
			sua.setFrequency(new PeriodicActivity(opening,closing,start,end,cadence));
			daoAc.updateActivityJSON(sua);
		}
		return sua;
	}
	
	
	
}
