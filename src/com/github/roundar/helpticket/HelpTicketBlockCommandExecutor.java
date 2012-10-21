package com.github.roundar.helpticket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpTicketBlockCommandExecutor implements CommandExecutor {

	HelpTicket helpTicket;
	
	public HelpTicketBlockCommandExecutor(HelpTicket helpTicket){		
		this.helpTicket = helpTicket;		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		if(args.length != 1)
			return false;
		
		Player target = sender.getServer().getPlayerExact(args[0]);
		
		if(target==null)			
			sender.sendMessage("Player does not exist.");
		
		else {			
			target.addAttachment(helpTicket).setPermission("HelpTicket.send", false);			
			sender.sendMessage(args[0] + " can no longer open new tickets." );			
		}
		
		return true;
		
	}

}
