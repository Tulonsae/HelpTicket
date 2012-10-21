package com.github.roundar.helpticket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TicketCountCommandExecutor implements CommandExecutor {

	HelpTicket helpTicket;
	
	public TicketCountCommandExecutor(HelpTicket helpTicket){		
		this.helpTicket = helpTicket;		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length != 0)
			return false;
		
		int count = helpTicket.ticketManager().countTickets();
		
		sender.sendMessage( "There is currently " + count + " ticket" + (count==1?"":"s") + " open." );
		
		return true;
			
	}

}
