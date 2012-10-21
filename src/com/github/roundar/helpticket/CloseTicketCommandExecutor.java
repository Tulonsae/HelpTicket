package com.github.roundar.helpticket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CloseTicketCommandExecutor implements CommandExecutor {

	HelpTicket helpTicket;
	
	public CloseTicketCommandExecutor(HelpTicket helpTicket){
		
		this.helpTicket = helpTicket;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length!=0)
			return false;
		
		if(helpTicket.ticketManager().closeTicket(sender.getName()))
			
			sender.sendMessage("Ticket Closed.");
		
		else
			
			sender.sendMessage("There are currently no tickets to close.");
		
		return true;
	}

}