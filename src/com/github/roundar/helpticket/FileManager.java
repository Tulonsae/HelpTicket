package com.github.roundar.helpticket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FileManager {
	
	private final ConfigAccessor openTickets, closedTickets;
	
	public FileManager(HelpTicket helpTicket) {
		
		openTickets = new ConfigAccessor(helpTicket, "openTickets.yml" );
		closedTickets = new ConfigAccessor(helpTicket, "closedTickets.yml" );
	}
		
	
	public int getLastUsedId(){	
		return openTickets.getConfig().getInt("id"); 
	}	
	
	
	public void saveTicket(Ticket ticket, boolean open){		
		ConfigAccessor file = open ? openTickets : closedTickets;
		
		List<String> tickets = file.getConfig().getStringList("tickets");
		tickets.add( ticket.toString() );
		
		file.getConfig().set("id", ticket.id() );
		file.getConfig().set("tickets", tickets );
		
		file.saveConfig();
	}
	
	
	public LinkedList<Ticket> loadOpenTickets() {
		//pull open tickets while also trimming closed tickets from openTickets.yml
		
		List<String> list = openTickets.getConfig().getStringList("tickets");
		
		LinkedList<Ticket> tickets = new LinkedList<Ticket>();
		
		if(list.isEmpty())
			return tickets;
		
		int lastClosedId = closedTickets.getConfig().getInt("id");
		
		Ticket ticket;
		
		for(int i=0; i<list.size(); i++) {
			
			ticket = new Ticket( list.get(i) );
			
			if(ticket.id() > lastClosedId)
				tickets.add( ticket );
			else
				list.remove(i);
		}
		
		openTickets.getConfig().set("tickets", list );
		openTickets.saveConfig();
		
		return tickets;
	}
}
