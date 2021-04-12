package logic.model;



/**
 * 
 * @author sav
 *
 *roba che ho fatto per convincere giovanni e andrea che forse se pò fare così e perché poi mi scordo quello che ho fatto
 *
 *stavo guardando il class diagram e uno dei problemi che mi è venuto in mente è che tipo tramite polimorfismo
 *dovremmo fare in modo che le activity e le activity certificate assumano due comportamenti diversi
 *
 *mi spiego meglio: per quanto definito quello che succede è che se l'utente fa una challenge di un partner riceve una sorta ricompensa
 *ma non accade per le challenge non verificate
 *
 *allora quello che famo è creare una fabbrica di challenge che crea i due tipi di challenge che implementano la stessa interfaccia
 *che poi userà l'utente
 *
 *funziona più o meno così: utente o partner chiama la factory e le chiede di creare una activity
 *l'activity sarà di tipo "normal" se a chiamare la factory è l'utente o "certified" se è un partner
 *
 *la factory quindi prenderà l'activity vector (singleton) e ci infilerà l'activity
 *
 *ipotesi da confermare:
 *forse si potrebbe affidare la responsabilità di factory all'activity vector
 *
 *vantaggi: non abbiamo una classe in più da mantere 
 * mi pare abbia senso che la sua factory sia l'array stesso
 * 
 *svantaggi: non me ne vengono in mente tbh
 */


public class User extends SuperUser{
	
	private Schedule schedule;
	private int wallet;
	
	public User(String username, Long id) {
		super(username, id);
		wallet = 0;
		schedule = new Schedule();
	}
	
	public int getBalance() {
		return wallet;
	}
	
	
	public void getReward(int reward) {
		wallet+=reward;
	};
	
	public Schedule getSchedule() {
		return schedule;
	};
	
	
	
}