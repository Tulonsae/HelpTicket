package com.github.roundar.helpticket;

import java.util.LinkedList;

public class TicketManager {
	

	private HelpTicket helpTicket;
	
	private LinkedList<Ticket> tickets;
	
	private int currentId;
	
	
	public TicketManager(HelpTicket helpTicket) {
		
		this.helpTicket = helpTicket;
		
		tickets = helpTicket.fileManager().loadOpenTickets();
		
		currentId = helpTicket.fileManager().getLastUsedId() + 1;
	}
	
	public int countTickets(){ 
		return tickets.size(); 
	}
	
	public Ticket readTicket(){ 
		return tickets.isEmpty() ? null : tickets.peek();
	}
	
	public void passTicket(){
		if(!tickets.isEmpty())
			tickets.add( 1, tickets.remove() );
	}
	
	public void openTicket(String sender, String message){
		
		tickets.add( new Ticket( currentId++, sender, message) );
		
		helpTicket.fileManager().saveTicket( tickets.peekLast(), true );
	}
	
	
	public boolean closeTicket(String closer){
		
		if(tickets.isEmpty())
			return false;
		
		tickets.peek().setCloser(closer);

		helpTicket.fileManager().saveTicket( tickets.remove(), false );
		
		return true;
	}
	
}
