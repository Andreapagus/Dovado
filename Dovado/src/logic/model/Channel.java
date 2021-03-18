package logic.model;

import java.util.ArrayList;
import logic.model.Message;

public class Channel {
	
	private ArrayList<Message> listOfMsg;
	private int sizeList;
	
	//Utile solo se vogliamo aggiornare dinamicamente senza prendere tutta la chat. Probabilmente verrà cancellato
	private Message lastMessage;

	
	public Channel () {
		
		listOfMsg = new ArrayList<Message>();
		sizeList = listOfMsg.size();
		
	//Qui si inserirà  un eventuale recupero della chat dalla persistenza. Per il momento assumiamo sempre nulla
		lastMessage = new Message();
		listOfMsg.add(lastMessage);
	}
	
	
	public void addMsg(int user, String msgText) {

		Message newMessage = new Message(user, msgText); 
		listOfMsg.add(newMessage);

	}
		
	
	//Da vedere meglio, potrebbe essere necessario passare gli attributi invece della classe Message. Per il momento non è usato
	public void removeMsg(Message msg) {
		
		for (int i = 0; i < sizeList; i++) {
			
			if (msg.equals(listOfMsg.get(i))){
				
				listOfMsg.remove(i);
			}
		}
	}
	
	public ArrayList<Message> getChat(){
		return this.listOfMsg;
	}
	
	
	//IL METODO returnChat SECONDO ME VA NEL CONTROLLER, QUINDI QUESTI PER IL MOMENTO RESTANO INUTILIZZATI
	public ArrayList<String> returnFormattedChat() {
		return returnFormattedChat(0);
	}
	
	public ArrayList<String> returnFormattedChat(int startIndex) {
		
		ArrayList<String> chat = new ArrayList<String>();
		
		for (int i = startIndex; i < sizeList; i++) {
			
			chat.add(listOfMsg.get(i).getUsr() + "::" + listOfMsg.get(i).getMsgSentDate() + " ----> " + listOfMsg.get(i).getMsgText()+"\n");
		}
		/**if (chat.get(0) == null) {
			System.out.println("sono vuoto");
		}
		else System.out.println(chat.get(0));**/
		return chat;
	}

}
