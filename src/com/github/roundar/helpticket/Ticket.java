package com.github.roundar.helpticket;

import java.text.ParseException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ticket {
	
	private final Short id;
	private final String date;
	private final String opener;
	private final String message;
	
	private String closer;
	
	public Ticket(Short id, String opener, String message){		
		this.id = id;
		this.date = new Date().toString();
		this.opener = opener;
		this.message = message;
		this.closer = null;
	}
	
	public Ticket(String ticketString) throws ParseException {
		
		Scanner scanner = new Scanner(ticketString);
		scanner.useDelimiter(",");
		
		try {
			id = scanner.nextShort();		
			date = scanner.next();		
			opener = scanner.next();			
			message = scanner.next();			
		} catch (NumberFormatException NFE ) {
			scanner.close();
			throw new ParseException("Index invalid or missing.",0);
		} catch (NoSuchElementException NSEE) {
			scanner.close();
			throw new ParseException("Ticket string corrupted, missing the required date, opener, or message.",0);
		}
		

		try {
			closer = scanner.next();
		} catch(NoSuchElementException NSEE) {}
		
		scanner.close();
		
		
		
	}
	
	
	public Short id(){ return id; }
	
	public String date() { return date; }
	
	public String opener() { return opener; }

	public String message() { return message; }
	
	public String closer() { return closer; }
	
	public void setCloser(String closer) { this.closer = closer; }
	
	
	public String toString(){
		
		String temp = (closer==null? "" : closer);
		
		return id.toString() + "," + date + "," + opener + "," + message + "," + temp;
	}

	
	
}
