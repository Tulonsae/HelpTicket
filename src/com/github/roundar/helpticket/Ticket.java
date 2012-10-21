package com.github.roundar.helpticket;

import java.util.Date;

public class Ticket {
	
	private int id;
	private String date;
	private String opener;
	private String message;
	
	private String closer;
	
	public Ticket(int id, String opener, String message){		
		this.id = id;
		this.date = new Date().toString();
		this.opener = opener;
		this.message = message;
		this.closer = null;
	}
	
	public Ticket(String ticketString) {
		
		String[] ticket = ticketString.split(",");
		
		try {			
			id = Short.parseShort(ticket[0]);			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			id = 666666666;
		}
		
		try {
			date = ticket[1];
			opener = ticket[2];
			message = ticket[3];
			
			if(ticket.length > 4)
				closer = ticket[4];		
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			date = date==null? "": date;
			opener = opener==null? "":opener;
			message = message==null? "":message;
			closer = ticketString; //preserve potentially corrupted string
		}
	}
	
	
	public int id(){ return id; }
	
	public String date() { return date; }
	
	public String opener() { return opener; }

	public String message() { return message; }
	
	public String closer() { return closer; }
	
	public void setCloser(String closer) { this.closer = closer; }
	
	
	public String toString(){
		
		return id + "," + date + "," + opener + "," + message + "," + (closer==null? "" : closer);
	}
	
}
