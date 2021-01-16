package logic.model;


import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Message {
	private String msgText;
	private int user;
	private LocalDateTime sentDate;
	
	public Message() {
		this(0,"");
	}
	
	public Message (int user, String msgText) {
		
	   this.user = user;
	   this.msgText = msgText;
	   this.sentDate = LocalDateTime.now();		
	}
	
	//Non faccio Override poiché non controllo gli Objects in generale ma solo i Message
	public boolean equals(Message m) {
		if ( m != null) {
			
			return this.user == m.getUsr() &&
					this.getMsgSentDate().equals(m.getMsgSentDate()) &&
					 this.msgText == m.getMsgText()	;
		}
		else return false;
	}	
	
	public String getMsgText() {
		return msgText;
	
	}
	
	public int getUsr() {
		return this.user;
	}		
	
	
	//"yyyy-MM-dd'T'HH:mm:ss.SSS" -- "yyyy/MM/dd HH:mm:ss"
	public String getMsgSentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		return dtf.format(sentDate);
	}
	
	public void setSentDate(String Date) {
		this.sentDate = LocalDateTime.parse(Date);
	}
	
	public static void main(String[] args) {
		
	}
		
}
