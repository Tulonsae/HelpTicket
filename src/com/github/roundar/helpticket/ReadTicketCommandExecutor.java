package com.github.roundar.helpticket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReadTicketCommandExecutor implements CommandExecutor {

	HelpTicket helpTicket;
	
	public ReadTicketCommandExecutor(HelpTicket helpTicket){
		
		this.helpTicket = helpTicket;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length != 0)
			return false;
		
		Ticket ticket = helpTicket.ticketManager().readTicket();
		
		if(ticket==null)
			
			sender.sendMessage("There are currently no open tickets.");
		
		else
			
			sender.sendMessage(	"\nOpener:  " + ticket.opener() + "\n\n" + ticket.message() + "\n\n " );
		
		return true;
		
	}

}
