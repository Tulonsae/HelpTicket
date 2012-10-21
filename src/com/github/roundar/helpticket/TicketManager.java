package com.github.roundar.helpticket;

import java.util.LinkedList;

public class TicketManager {
	

	private HelpTicket helpTicket;
	
	private LinkedList<Ticket> tickets;
	
	private Short currentId;
	
	
	public TicketManager(HelpTicket helpTicket) {
		
		this.helpTicket = helpTicket;
		
		tickets = helpTicket.fileManager().getOpenTickets();
		
		if(tickets.isEmpty())
			currentId = 0;
		else
			currentId = (short) (tickets.peekLast().id() + 1);
		
	}
	
	public int countTickets(){
		
		return tickets.size();
	}

	
	public void passTicket(){
		if(!tickets.isEmpty())
			tickets.add( tickets.remove() );
	}
	
	
	public Ticket readTicket(){
		
		if(tickets.isEmpty())
			return null;
		
		return tickets.peek();
	}
	
	
	public void openTicket(String sender, String message){
		
		Ticket ticket = new Ticket( currentId , sender, message);
		
		tickets.add(ticket);
		
		helpTicket.fileManager().appendOpenTicket(ticket);
		
		currentId++;		
	}
	
	
	public boolean closeTicket(String closer){
		
		if(tickets.isEmpty())
			return false;
		
		Ticket ticket = tickets.remove();
		ticket.setCloser(closer);

		helpTicket.fileManager().archiveTicket(ticket);
		
		return true;		
	}
	
}
