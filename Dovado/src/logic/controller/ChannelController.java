package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.model.*;
public class ChannelController {
	
	private Channel channell;	
	
	public ChannelController(Activity activity) {
		this.channell = activity.getChannel();
		}
	
	public void writeMessage(int user, String textMsg) {
		this.channell.addMsg(user, textMsg);
	}
	
	
	public ArrayList<String[]> formattedChat(int user){
		ArrayList<Message> listOfMsg = this.channell.getChat();
		ArrayList<String[]> chat = new ArrayList<String[]>();
		String[] msg;
		if (listOfMsg.size() == 1) {
			return null;
		}
		for (int i = 1; i < listOfMsg.size() ; i++) {
			msg = new String[2];
			if (listOfMsg.get(i).getUsr() == user) {
				msg[0] = "0";
			}
			else msg[0] = "1";
			
			msg[1] = listOfMsg.get(i).getMsgSentDate() + " ----> " + listOfMsg.get(i).getMsgText()+"\n";
			chat.add(msg);
		}
		return chat;
	}
}
