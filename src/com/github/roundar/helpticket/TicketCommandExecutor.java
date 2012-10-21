package com.github.roundar.helpticket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TicketCommandExecutor implements CommandExecutor {

	private HelpTicket helpTicket;
	
	public TicketCommandExecutor(HelpTicket helpTicket){		
		this.helpTicket = helpTicket;		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length==0)
			return false;
		
		StringBuffer message = new StringBuffer();
		
		for(String arg: args)
			message.append(arg);
		
		
		helpTicket.ticketManager().openTicket( sender.getName(), message.toString() );
		
		sender.sendMessage("Your ticket has been submited.");
		
		return true;
		
	}
}
