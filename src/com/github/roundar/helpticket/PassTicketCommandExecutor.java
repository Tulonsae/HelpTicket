package com.github.roundar.helpticket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PassTicketCommandExecutor implements CommandExecutor {

	HelpTicket helpTicket;
	
	public PassTicketCommandExecutor(HelpTicket helpTicket){		
		this.helpTicket = helpTicket;		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length != 0)
			return false;
		
		TicketManager ticketManager = helpTicket.ticketManager();
		
		if (ticketManager.countTickets() == 0)			
			sender.sendMessage("There are currently no open tickets.");
		
		else if (ticketManager.countTickets() == 1)			
			sender.sendMessage("There is only one open ticket.");
		
		else {
			ticketManager.passTicket();			
			sender.sendMessage("Ticket has been moved back.");			
		}
		
		return true;
		
	}

}
